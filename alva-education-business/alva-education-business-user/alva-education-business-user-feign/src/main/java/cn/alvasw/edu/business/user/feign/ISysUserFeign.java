package cn.alvasw.edu.business.user.feign;

import cn.alvasw.edu.data.user.entity.SysUser;
import cn.alvasw.framework.commons.base.result.Rs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@FeignClient("user-server")
@RequestMapping("/sys/user")
public interface ISysUserFeign {

	/**
	 * 通过 account 查询 SysUser
	 * @param account 用户名
	 * @return 查找结果
	 */
	@RequestMapping("/find/account")
	Rs<SysUser> find(@RequestParam("account") String account);

}
