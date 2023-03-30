package cn.alvasw.edu.business.user.service;

import cn.alvasw.edu.data.user.entity.SysUser;
import cn.alvasw.edu.data.user.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public interface ITeacherService extends IService<Teacher> {

	/**
	 * 按邮箱查询或创建教师
	 * @param email 邮箱
	 * @return 教室
	 */
	Teacher findOrCreateByEmail(String email);

}