package cn.alvasw.edu.business.user.controller;

import cn.alvasw.edu.business.user.service.IUserService;
import cn.alvasw.edu.data.user.entity.User;
import cn.alvasw.edu.data.user.vo.input.UserVO;
import cn.alvasw.framework.commons.base.result.Rs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-01
 */
@RestController
@RequestMapping("/user/wx")
@Slf4j
public class UserController {

	@Resource
	private IUserService userService;

	@RequestMapping("/login")
	public Rs<User> login(@RequestBody UserVO userVO) {
		log.info("[用户登录请求] userVO -> [{}]", userVO);
		return Rs.assertNull(userService.findOrCreateByEmail(userVO), "登录成功", "登录失败");
	}

}
