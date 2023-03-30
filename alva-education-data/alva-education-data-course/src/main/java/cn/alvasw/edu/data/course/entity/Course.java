package cn.alvasw.edu.data.course.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class Course extends BaseEntity {

	private Long     id;
	private Long     typeId;
	private Long     teacherId;
	private String     name;
	private String     image;
	private String     info;
	private BigDecimal price;
	private Date       beginTime;
	private Date       endTime;

}
