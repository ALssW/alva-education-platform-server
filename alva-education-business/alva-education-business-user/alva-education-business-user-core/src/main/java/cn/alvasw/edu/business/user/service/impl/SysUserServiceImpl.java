package cn.alvasw.edu.business.user.service.impl;

import cn.alvasw.edu.business.user.dao.SysUserDao;
import cn.alvasw.edu.business.user.service.ISysUserService;
import cn.alvasw.edu.data.user.entity.SysUser;
import cn.alvasw.framework.commons.base.exception.ServiceException;
import cn.alvasw.framework.commons.base.result.RsCodes;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {
	@Override
	public SysUser getByAccount(String account) {
		return this.query().eq("account", account).one();
	}

	@Override
	public SysUser getByPass(String account, String password) {
		return this.query().eq("account", account).eq("password", password).one();
	}

	@Override
	public boolean save(SysUser sysUser) {
		if (this.getByAccount(sysUser.getAccount()) != null) {
			throw new ServiceException(RsCodes.FAIL, "账号已存在");
		}
		return super.save(sysUser);
	}
}
