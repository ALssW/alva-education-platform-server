package cn.alvasw.edu.data.activity.bo;

import lombok.Data;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-18
 */
@Data
public class ActivityRedPacketCouponBO {

	private Long    id;
	private Long    couponId;
	private Integer nums;
	private Integer probability;

}
