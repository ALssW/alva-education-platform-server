package cn.alvasw.edu.business.course.service.impl;

import cn.alvasw.edu.business.course.dao.CourseDao;
import cn.alvasw.edu.business.course.service.ICourseService;
import cn.alvasw.edu.business.course.service.ICourseTypeService;
import cn.alvasw.edu.business.user.feign.TeacherFeign;
import cn.alvasw.edu.data.course.entity.Course;
import cn.alvasw.edu.data.course.entity.CourseType;
import cn.alvasw.edu.data.user.entity.Teacher;
import cn.alvasw.framework.commons.base.exception.ServiceException;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import cn.alvasw.framework.commons.web.annotation.Auth;
import cn.alvasw.framework.commons.web.utils.AuthUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements ICourseService {

	@Resource
	private TeacherFeign teacherFeign;

	@Resource
	private ICourseTypeService courseTypeService;

	@Override
	@Auth
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Course course) {
		Long teacherId = AuthUtil.getRemoveUid();
		Teacher teacher = Rs.isOk(teacherFeign.find(teacherId));

		if (teacher == null){
			throw new ServiceException(RsCodes.FAIL,"不存在该教师");
		}
		course.setTeacherId(teacherId);
		CourseType courseType = courseTypeService.getById(course.getTypeId());
		if (courseType == null){
			throw new ServiceException(RsCodes.FAIL,"不存在该分类");
		}

		if (!CourseType.MIN_TYPE.equals(courseType.getType())){
			throw new ServiceException(RsCodes.FAIL,"该级分类下不允许创建课程");
		}
		courseTypeService.updateNums(course.getTypeId(), 1);

		return super.save(course);
	}

	@Override
	@Auth
	public List<Course> listByTcrId() {
		return query().eq("teacher_id", AuthUtil.getRemoveUid()).list();
	}

}
