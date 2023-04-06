package cn.alvasw.edu.data.course.entity;

import cn.alvasw.edu.data.course.handler.LongListTypeHandler;
import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class CourseType extends BaseEntity {

	public static final Integer MIN_TYPE = 3;

	@TableId(type = IdType.ASSIGN_ID)
	private Long       id;
	private Long       pid;
	private String     name;
	private Integer    num;
	private Integer    type;
	@TableField(typeHandler = LongListTypeHandler.class)
	private List<Long> chains;

}
