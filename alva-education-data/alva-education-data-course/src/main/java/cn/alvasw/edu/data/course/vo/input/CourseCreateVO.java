package cn.alvasw.edu.data.course.vo.input;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class CourseCreateVO extends BaseEntity {

	@NotNull(message = "课程分类不可为空")
	private Long          typeId;
	@NotNull(message = "教师不可为空")
	private Long          teacherId;
	@NotBlank(message = "课程名称不可为空")
	private String        name;
	@NotBlank(message = "课程图片不可为空")
	private String        image;
	private String        info;
	private BigDecimal    price;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

}
