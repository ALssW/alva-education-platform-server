package cn.alvasw.edu.business.activity.service.impl;

import cn.alvasw.edu.business.activity.dao.ActivityDao;
import cn.alvasw.edu.business.activity.service.ActivityService;
import cn.alvasw.edu.data.activity.bo.ActivityBO;
import cn.alvasw.edu.data.activity.bo.ActivityRedPacketCashBO;
import cn.alvasw.edu.data.activity.bo.ActivityRedPacketCouponBO;
import cn.alvasw.edu.data.activity.entity.Activity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 针对表【activity(活动)】的数据库操作Service实现
 *
 * @author ALsW
 * @date 2023-04-17 14:38:39
 */
@Service
@Slf4j
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, Activity>
		implements ActivityService {

	@Resource
	private ActivityDao activityDao;

	@Resource
	private StringRedisTemplate redisClient;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateStatus(Long id, Integer status) {
		boolean updateFlag;
		// 修改活动状态
		updateFlag = this.update().set("status", status)
				.set("update_time", new Date())
				.eq("id", id)
				.update();

		if (status == 0) {
			Set<String> keySets = redisClient.keys("activity*" + id + "*");
			if (keySets == null) {
				return updateFlag;
			}
			redisClient.delete(keySets);
			return updateFlag;
		}

		// 活动预热
		// 查询活动对应的所有红包信息(现金红包、优惠券红包)
		ActivityBO activityBO = activityDao.queryInfoByActId(id);
		log.debug("[更新活动状态] 上架 activityBO -> {}", activityBO);

		// redis 流水线预热活动
		List<Object> executePipelined = redisClient.executePipelined(new SessionCallback<Boolean>() {
			@Override
			@SuppressWarnings("unchecked")
			public Boolean execute(@NotNull RedisOperations operations) throws DataAccessException {
				// 存入redis中
				String activityKey = "activity:action:" + activityBO.getId();

				HashOperations<String, String, String> opsForHash = operations.opsForHash();
				opsForHash.put(activityKey, "beginTime", activityBO.getBeginTime().getTime() + "");
				opsForHash.put(activityKey, "endTime", activityBO.getEndTime().getTime() + "");
				opsForHash.put(activityKey, "type", activityBO.getType() + "");


				String typeListKey = activityKey + ":type";
				// 获得活动中的现金红包
				ActivityRedPacketCashBO cashBo = activityBO.getCashBO();
				// 获得活动中的优惠券红包
				List<ActivityRedPacketCouponBO> couponBOList = activityBO.getCouponBOList();

				// 对应的红包类型放入集合中
				ListOperations<String, String> opsForList = operations.opsForList();
				// 获取现金红包额概率
				Integer moneyProbability = cashBo.getProbability();
				for (int i = 0; i < moneyProbability; i++) {
					opsForList.leftPush(typeListKey, "0");
				}
				// 获取优惠券红包的概率
				Integer couponProbability = couponBOList.get(0).getProbability();
				// 准备一个 List 集合,将对应的红包类型放入集合中
				for (int i = 0; i < couponProbability; i++) {
					opsForList.leftPush(typeListKey, "1");
				}

				// 现金红包 Hash 结构
				String cashKey = "activity:cash:" + activityBO.getId();
				opsForHash.put(cashKey, "cash", cashBo.getCash().multiply(BigDecimal.valueOf(100))
						.longValue() + "");
				opsForHash.put(cashKey, "nums", cashBo.getNums() + "");
				opsForHash.put(cashKey, "type", cashBo.getType() + "");


				String couponKey = "activity:coupon:" + activityBO.getId();
				for (ActivityRedPacketCouponBO couponBo : couponBOList) {
					// 优惠券 Set 结构
					operations.opsForSet().add(couponKey, couponBo.getCouponId() + "");

					// 优惠券 Hash 结构
					opsForHash.put(couponKey + ":nums", couponBo.getCouponId() + "", couponBo.getNums() + "");
				}
				return null;
			}
		});


		return updateFlag;
	}
}




