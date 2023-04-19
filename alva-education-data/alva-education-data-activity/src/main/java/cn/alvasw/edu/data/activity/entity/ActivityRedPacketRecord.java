package cn.alvasw.edu.data.activity.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 活动红包领取记
 *
 * @author ALsW
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "activity_red_packet_record")
@Data
public class ActivityRedPacketRecord extends BaseEntity implements Serializable {
	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 活动ID
	 */
	private Long activityId;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 红包类型(0-现金 1-优惠券)
	 */
	private Integer type;

	/**
	 * 记录信息(现金-金额，优惠券-优惠券ID)
	 */
	private String info;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
}