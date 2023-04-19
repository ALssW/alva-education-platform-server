package cn.alvasw.edu.data.activity.vo.input;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@Data
public class ActivityRedPacketContentVO {

	private Long activityId;
	private Integer probability;
	private Integer contentType;

	private BigDecimal cash;
	private Integer cashNums;
	private Integer cashType;

	private Long couponId;
	private Integer couponNums;

}
