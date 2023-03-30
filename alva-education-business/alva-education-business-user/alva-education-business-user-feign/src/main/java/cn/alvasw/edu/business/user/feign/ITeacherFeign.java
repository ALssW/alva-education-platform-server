package cn.alvasw.edu.business.user.feign;

import cn.alvasw.edu.data.user.entity.Teacher;
import cn.alvasw.framework.commons.base.result.Rs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@FeignClient(value = "server-user", contextId = "sys")
@RequestMapping("/user/teacher")
public interface ITeacherFeign {


	/**
	 * 教师登录
	 *
	 * @param email 邮箱
	 * @param code  验证码
	 * @return 教师
	 */
	@RequestMapping("/login")
	Rs<Teacher> login(String email, String code);
}
