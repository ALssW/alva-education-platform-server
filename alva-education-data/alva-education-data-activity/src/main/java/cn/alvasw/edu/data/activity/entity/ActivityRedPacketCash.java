package cn.alvasw.edu.data.activity.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 活动现金红包
 * @author ALsW
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="activity_red_packet_cash")
@Data
public class ActivityRedPacketCash extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 红包ID
     */
    private Long redPacketId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 金额
     */
    private BigDecimal cash;

	/**
	 * 红包数量
	 */
	private Integer nums;

	/**
	 * 红包类型
	 */
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}