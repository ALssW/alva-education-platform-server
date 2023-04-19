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
 * 活动优惠券红包
 * @author ALsW
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="activity_red_packet_coupon")
@Data
public class ActivityRedPacketCoupon extends BaseEntity implements Serializable {
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
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 优惠券数量(-1-无限制)
     */
    private Integer nums;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}