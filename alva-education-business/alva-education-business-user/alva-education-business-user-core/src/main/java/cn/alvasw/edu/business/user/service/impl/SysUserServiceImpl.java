package cn.alvasw.edu.business.user.service.impl;

import cn.alvasw.edu.business.user.dao.ISysUserDao;
import cn.alvasw.edu.business.user.service.ISysUserService;
import cn.alvasw.edu.data.user.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserDao, SysUser> implements ISysUserService {
	@Override
	public SysUser getByUsername(String username) {
		return this.query().eq("username", username).one();
	}
}
