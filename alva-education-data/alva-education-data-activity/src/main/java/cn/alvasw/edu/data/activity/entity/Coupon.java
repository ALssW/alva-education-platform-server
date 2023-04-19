package cn.alvasw.edu.data.activity.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Coupon extends BaseEntity implements Serializable {

	@TableId(type = IdType.NONE)
	private Long id;

	private String subject;

	private Integer nums;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

}
