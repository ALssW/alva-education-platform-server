package cn.alvasw.edu.data.course.vo.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
@Data
public class CourseTypeCreateVO {

	private Long   pid;
	@NotBlank(message = "分类名称不能为空")
	private String name;

}
