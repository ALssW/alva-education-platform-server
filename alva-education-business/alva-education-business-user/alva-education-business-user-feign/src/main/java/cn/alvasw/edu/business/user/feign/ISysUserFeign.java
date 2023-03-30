package cn.alvasw.edu.business.user.feign;

import cn.alvasw.edu.data.user.vo.output.SysUserQueryVO;
import cn.alvasw.framework.commons.base.result.Rs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@FeignClient(value = "server-user", contextId= "tcr")
@RequestMapping("/user/sys")
public interface ISysUserFeign {

	/**
	 * 通过 account 查询 SysUser
	 * @param account 用户名
	 * @return 查找结果
	 */
	@RequestMapping("/find/account")
	Rs<SysUserQueryVO> find(@RequestParam("account") String account);

	/**
	 * 按账号与密码查询系统用户
	 *
	 * @param account  账号
	 * @param password 密码
	 * @return 系统用户
	 */
	@RequestMapping("/find/pass")
	Rs<SysUserQueryVO> find(@RequestParam("account") String account, @RequestParam("password") String password);

}
