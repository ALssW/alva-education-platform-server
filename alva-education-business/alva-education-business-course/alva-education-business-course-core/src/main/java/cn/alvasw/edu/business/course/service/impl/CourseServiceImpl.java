package cn.alvasw.edu.business.course.service.impl;

import cn.alvasw.edu.business.course.dao.CourseDao;
import cn.alvasw.edu.business.course.service.ICourseService;
import cn.alvasw.edu.data.course.entity.Course;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseDao,Course> implements ICourseService{
}
