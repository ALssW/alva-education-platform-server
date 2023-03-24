package cn.alvasw.edu.data.course.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
@Data
@Accessors(chain = true)
public class CourseTypeTreeVO implements Serializable {

	private Long                   id;
	private Long                   pid;
	private String                 label;
	private Integer                type;
	private List<CourseTypeTreeVO> children;

	public void addChildren(CourseTypeTreeVO treeVO) {
		if (this.children == null) {
			this.children = new ArrayList<>(2);
		}

		children.add(treeVO);
	}
}
