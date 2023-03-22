package cn.alvasw.edu.business.user.service;

import cn.alvasw.edu.data.user.entity.SysUser;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public interface ISysUserService {
	/**
	 * 按用户名查询系统用户
	 * @param username 用户名
	 * @return 系统用户
	 */
	SysUser getByUsername(String username);
}
