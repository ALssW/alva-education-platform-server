package cn.alvasw.edu.business.user.controller;

import cn.alvasw.edu.business.user.service.ISysUserService;
import cn.alvasw.edu.data.user.entity.SysUser;
import cn.alvasw.framework.commons.base.result.Rs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/user/sys")
@Slf4j
public class SysUserController {

	@Resource
	private ISysUserService sysUserService;

	@RequestMapping("/find/username")
	public Rs<SysUser> findByUsername(@NotBlank String username) {
		log.info("username -> [{}]", username);
		SysUser sysUser = sysUserService.getByUsername(username);
		return Rs.assertNull(sysUser);
	}

}
