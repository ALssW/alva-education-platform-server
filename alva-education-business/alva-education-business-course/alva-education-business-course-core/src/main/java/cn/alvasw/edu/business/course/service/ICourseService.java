package cn.alvasw.edu.business.course.service;

import cn.alvasw.edu.data.course.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
public interface ICourseService extends IService<Course> {

	@Override
	boolean save(Course course);

	List<Course> listByTcrId();
}
