package cn.alvasw.edu.data.activity.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-18
 */
@Data
public class ActivityBO {

	private Long    activityId;
	private String  title;
	private String  url;
	private Integer type;
	private Date    beginTime;
	private Date    endTime;

	private ActivityRedPacketCashBO cashBo;

	List<ActivityRedPacketCouponBO> couponBOList;

}
