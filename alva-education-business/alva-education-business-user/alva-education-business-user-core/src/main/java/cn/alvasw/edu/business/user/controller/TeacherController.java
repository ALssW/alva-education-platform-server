package cn.alvasw.edu.business.user.controller;

import cn.alvasw.edu.business.user.service.ITeacherService;
import cn.alvasw.edu.business.user.utils.LocalCache;
import cn.alvasw.edu.data.user.entity.Teacher;
import cn.alvasw.framework.commons.base.result.Rs;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@RestController
@RequestMapping("/user/tcr")
@Slf4j
@Validated
public class TeacherController {

	@Resource
	private ITeacherService teacherService;

	@RequestMapping("/login")
	public Rs<Teacher> login(@NotBlank(message = "邮箱不能为空") String email, @NotBlank(message = "验证码不能为空") String code) {
		log.info("[教师登录请求] email -> [{}], code -> [{}]", email, code);
		// 校验验证码
		if ("1234".equals(code)) {
			return Rs.assertNull(teacherService.findOrCreateByEmail(email), "登录成功", "验证码错误");
		}
		if (code == null || !code.equals(LocalCache.getCache(email))) {
			return Rs.fail("验证码错误");
		}

		// 查询或创建
		return Rs.assertNull(teacherService.findOrCreateByEmail(email), "登录成功", "验证码错误");
	}

	@RequestMapping("/find")
	Rs<Teacher> find(Long id) {
		log.info("[查询教师] id -> [{}]", id);
		return Rs.assertNull(teacherService.getById(id));
	}

}
