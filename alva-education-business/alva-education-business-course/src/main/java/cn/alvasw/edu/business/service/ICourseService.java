package cn.alvasw.edu.business.service;

import cn.alvasw.edu.business.entity.Course;
import cn.alvasw.framework.commons.base.result.Rs;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
public interface ICourseService extends IService<Course> {
	/**
	 * 通过 Excel 表格新增课程列表
	 * @param excel Excel 表格
	 * @return 新增结果
	 */
	Rs<Void> addCourses(MultipartFile excel);


}
