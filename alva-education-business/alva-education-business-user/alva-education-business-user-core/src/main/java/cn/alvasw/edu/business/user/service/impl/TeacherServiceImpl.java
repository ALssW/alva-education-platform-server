package cn.alvasw.edu.business.user.service.impl;

import cn.alvasw.edu.business.user.dao.TeacherDao;
import cn.alvasw.edu.business.user.service.ITeacherService;
import cn.alvasw.edu.data.user.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Service
@Slf4j
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements ITeacherService {

	@Override
	public Teacher findOrCreateByEmail(String email) {
		Teacher teacher = query().eq("email", email).one();
		if (teacher != null) {
			log.debug("教师存在");
			return teacher;
		}

		teacher = new Teacher().setEmail(email).setName("教师-" + Math.round(Math.random() * 1000));
		log.debug("教师不存在，将创建教师 teacher -> [{}]", teacher);
		return save(teacher) ? teacher : null;
	}
}
