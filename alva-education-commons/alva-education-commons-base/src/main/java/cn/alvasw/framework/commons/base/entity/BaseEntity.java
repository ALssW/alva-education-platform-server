package cn.alvasw.framework.commons.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-15
 */
@Data
public class BaseEntity {

	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;
	@TableField(fill = FieldFill.INSERT)
	private Integer       status;
	@TableField(fill = FieldFill.INSERT)
	private Integer       delFlag;

}
