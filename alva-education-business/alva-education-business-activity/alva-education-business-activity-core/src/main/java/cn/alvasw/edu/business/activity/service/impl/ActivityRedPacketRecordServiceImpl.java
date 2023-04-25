package cn.alvasw.edu.business.activity.service.impl;

import cn.alvasw.edu.business.activity.dao.ActivityRedPacketRecordDao;
import cn.alvasw.edu.business.activity.service.ActivityRedPacketRecordService;
import cn.alvasw.edu.business.activity.service.CouponUserRelationService;
import cn.alvasw.edu.business.activity.service.UserMoneyDetailService;
import cn.alvasw.edu.data.activity.entity.ActivityRedPacketRecord;
import cn.alvasw.edu.data.activity.entity.CouponUserRelation;
import cn.alvasw.edu.data.activity.entity.UserMoneyDetail;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 针对表【activity_red_packet_acquire(活动红包领取记)】的数据库操作Service实现
 *
 * @author ALsW
 * @date 2023-04-17 14:38:39
 */
@Service
public class ActivityRedPacketRecordServiceImpl extends ServiceImpl<ActivityRedPacketRecordDao, ActivityRedPacketRecord>
		implements ActivityRedPacketRecordService {

	@Resource
	private UserMoneyDetailService userMoneyDetailService;

	@Resource
	private CouponUserRelationService couponUserRelationService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(ActivityRedPacketRecord record) {
		boolean saveFlag;
		super.save(record);

		if (record.getType() == 0) {
			// 记录到用户流水表中
			UserMoneyDetail moneyDetail = new UserMoneyDetail();
			moneyDetail.setUserId(record.getId());
			moneyDetail.setSource(0);
			moneyDetail.setDetail(BigDecimal.valueOf(Double.parseDouble(record.getInfo())));
			moneyDetail.setBusinessId(record.getActivityId());
			moneyDetail.setAction(0);
			saveFlag = userMoneyDetailService.save(moneyDetail);
		} else {
			// 优惠券
			CouponUserRelation couponUserRelation = new CouponUserRelation();
			couponUserRelation.setCouponId(Long.valueOf(record.getInfo()));
			couponUserRelation.setUserId(record.getUserId());
			couponUserRelation.setNums(1);
			couponUserRelation.setBusinessId(record.getActivityId());
			couponUserRelation.setSource(0);
			saveFlag = couponUserRelationService.save(couponUserRelation);
		}

		return saveFlag;
	}
}




