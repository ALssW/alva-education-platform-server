package cn.alvasw.edu.business.user.controller;

import cn.alvasw.edu.business.user.service.ITeacherService;
import cn.alvasw.edu.data.user.entity.Teacher;
import cn.alvasw.framework.commons.base.result.Rs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@RestController
@RequestMapping("/user/teacher")
@Slf4j
public class TeacherController {

	@Resource
	private ITeacherService teacherService;

	@RequestMapping("/login")
	public Rs<Teacher> login(String email, String code) {
		log.info("[教师登录请求] email -> [{}], code -> [{}]", email, code);
		// 校验验证码

		// 查询或创建
		return Rs.assertNull(teacherService.findOrCreateByEmail(email), "登录成功", "验证码错误");
	}

}
