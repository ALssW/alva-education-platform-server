package cn.alvasw.edu.data.activity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 活动优惠券红包
 * @author ALsW
 */
@TableName(value ="activity_red_packet_coupon")
@Data
public class ActivityRedPacketCoupon implements Serializable {
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 删除
     */
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}