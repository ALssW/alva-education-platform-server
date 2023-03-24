package cn.alvasw.edu.business.user.controller;

import cn.alvasw.edu.business.user.service.ISysUserService;
import cn.alvasw.edu.data.user.entity.SysUser;
import cn.alvasw.edu.data.user.vo.input.SysUserCreateVO;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 系统用户控制层(SysUser)
 *
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

	@Resource
	private ISysUserService sysUserService;

	/**
	 * 根据用户名查询系统用户
	 */
	@RequestMapping("/find/account")
	public Rs<SysUser> find(@NotBlank String account) {
		log.info("[接收到查询系统用户请求] account -> [{}]", account);
		return Rs.assertNull(sysUserService.getByAccount(account));
	}

	/**
	 * 创建系统用户
	 */
	@RequestMapping("/create")
	public Rs<SysUser> create(@Validated SysUserCreateVO sysUserVO) {
		log.info("[接收到系统用户创建请求] sysUserVO -> [{}]", sysUserVO);
		return Rs.assertBool(sysUserService.save(
				BeanUtil.copyInstance(
						sysUserVO,
						SysUser.class)));
	}

	@RequestMapping("/all")
	public Rs<List<SysUser>> all() {
		return Rs.assertEmpty(sysUserService.list());
	}

}
