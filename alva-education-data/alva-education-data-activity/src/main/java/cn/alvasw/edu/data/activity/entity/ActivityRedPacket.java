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
 * 活动红包
 * @author ALsW
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="activity_red_packet")
@Data
public class ActivityRedPacket extends BaseEntity implements Serializable {
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
     * 红包类型(0-现金红包 1-优惠价 2-)
     */
    private Integer type;

    /**
     * 概率
     */
    private Integer probability;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}