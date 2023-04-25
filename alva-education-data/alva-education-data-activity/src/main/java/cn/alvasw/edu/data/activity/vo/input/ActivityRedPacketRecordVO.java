package cn.alvasw.edu.data.activity.vo.input;

import lombok.Data;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-24
 */
@Data
public class ActivityRedPacketRecordVO {

	private Long activityId;
	private Long userId;
	private Integer type;
	private String info;

}
