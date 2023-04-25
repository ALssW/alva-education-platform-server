package cn.alvasw.edu.business.activity.service.impl;

import cn.alvasw.edu.business.activity.dao.ActivityRedPacketDao;
import cn.alvasw.edu.business.activity.service.ActivityRedPacketCashService;
import cn.alvasw.edu.business.activity.service.ActivityRedPacketCouponService;
import cn.alvasw.edu.business.activity.service.ActivityRedPacketService;
import cn.alvasw.edu.commons.cache.lock.RLock;
import cn.alvasw.edu.data.activity.entity.ActivityRedPacket;
import cn.alvasw.edu.data.activity.entity.ActivityRedPacketCash;
import cn.alvasw.edu.data.activity.entity.ActivityRedPacketCoupon;
import cn.alvasw.edu.data.activity.vo.input.ActivityRedPacketContentVO;
import cn.alvasw.framework.commons.core.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 针对表【activity_red_packet(活动红包)】的数据库操作Service实现
 *
 * @author ALsW
 * @date 2023-04-17 14:38:39
 */
@Service
@Slf4j
public class ActivityRedPacketServiceImpl extends ServiceImpl<ActivityRedPacketDao, ActivityRedPacket>
		implements ActivityRedPacketService {

	@Resource
	private ActivityRedPacketCashService cashService;

	@Resource
	private ActivityRedPacketCouponService couponService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	@RLock("#activityRedPacketContentVO.activityId + '' + #activityRedPacketContentVO.contentType")
	public boolean saveContent(ActivityRedPacketContentVO activityRedPacketContentVO) {
		Integer contentType = activityRedPacketContentVO.getContentType();


		ActivityRedPacket redPacket = this.query()
				.eq("ac_id", activityRedPacketContentVO.getActivityId())
				.eq("type", contentType).one();

		boolean saveFlag = false;
		if (redPacket == null) {
			redPacket = new ActivityRedPacket();
			redPacket.setActivityId(activityRedPacketContentVO.getActivityId());
			redPacket.setType(contentType);
			redPacket.setProbability(activityRedPacketContentVO.getProbability());
			saveFlag = this.save(redPacket);
		}

		if (contentType == 0) {
			//现金红包
			ActivityRedPacketCash cash = BeanUtil.copyInstance(activityRedPacketContentVO, ActivityRedPacketCash.class);
			if (cash == null) {
				return saveFlag;
			}
			cash.setActivityId(activityRedPacketContentVO.getActivityId());
			cash.setRedPacketId(redPacket.getId());
			cash.setNums(activityRedPacketContentVO.getCashNums());
			cash.setType(activityRedPacketContentVO.getCashType());
			log.info("[activity content save] 新增活动现金红包内容 - {}", cash);
			//保存
			saveFlag = cashService.save(cash);
		} else if (contentType == 1) {
			//优惠券红包
			ActivityRedPacketCoupon coupon = BeanUtil.copyInstance(activityRedPacketContentVO, ActivityRedPacketCoupon.class);
			if (coupon == null) {
				return saveFlag;
			}
			coupon.setActivityId(activityRedPacketContentVO.getActivityId());
			coupon.setRedPacketId(redPacket.getId());
			coupon.setNums(activityRedPacketContentVO.getCouponNums());
			log.info("[activity content insert] 新增活动优惠券红包内容 - {}", coupon);
			//保存
			saveFlag = couponService.save(coupon);
		}

		return saveFlag;
	}
}




