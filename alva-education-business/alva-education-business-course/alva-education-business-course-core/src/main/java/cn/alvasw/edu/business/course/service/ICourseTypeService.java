package cn.alvasw.edu.business.course.service;

import cn.alvasw.edu.data.course.entity.CourseType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
public interface ICourseTypeService extends IService<CourseType> {
	/**
	 * 按 pid 降序查询列表
	 *
	 * @return 降序列表
	 */
	List<CourseType> listDesc();
}
