package cn.alvasw.edu.data.activity.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-18
 */
@Data
public class ActivityRedPacketCashBO {

	private Long       cashId;
	private BigDecimal cash;
	private Integer    cashNums;
	private Integer    cashProbability;
	private Integer    cashType;

}
