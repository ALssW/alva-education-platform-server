package cn.alvasw.edu.business.user.service;

import cn.alvasw.edu.data.user.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public interface ISysUserService extends IService<SysUser> {
	/**
	 * 按账号查询系统用户
	 *
	 * @param account 账号
	 * @return 系统用户
	 */
	SysUser getByAccount(String account);

	/**
	 * 按账号与密码查询系统用户
	 *
	 * @param account  账号
	 * @param password 密码
	 * @return 系统用户
	 */
	SysUser getByPass(String account, String password);
}
