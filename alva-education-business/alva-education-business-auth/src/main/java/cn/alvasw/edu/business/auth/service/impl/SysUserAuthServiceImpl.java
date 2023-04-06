package cn.alvasw.edu.business.auth.service.impl;

import cn.alvasw.edu.business.auth.exception.AuthException;
import cn.alvasw.edu.business.auth.service.IAuthService;
import cn.alvasw.edu.business.user.feign.SysUserFeign;
import cn.alvasw.edu.data.auth.base.BaseUser;
import cn.alvasw.edu.data.auth.vo.input.LoginParamVO;
import cn.alvasw.edu.data.auth.vo.output.SysUserVO;
import cn.alvasw.edu.data.user.vo.output.SysUserQueryVO;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import cn.alvasw.framework.commons.core.utils.BeanUtil;
import com.alibaba.nacos.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Slf4j
@Service("authService-sys")
public class SysUserAuthServiceImpl implements IAuthService {

	@Resource
	private SysUserFeign sysUserFeign;

	@Override
	public BaseUser authLogin(LoginParamVO paramVO) {
		log.info("开始进行系统用户登录验证业务 -> [{}]", paramVO);

		// 参数校验
		if (StringUtils.isBlank(paramVO.getAccount()) || StringUtils.isBlank(paramVO.getPassword())) {
			throw new AuthException(RsCodes.BIND_ERROR, "用户名或密码为空");
		}

		// 获取用户信息
		SysUserQueryVO sysUser = Rs.isOk(sysUserFeign.find(paramVO.getAccount(), paramVO.getPassword()));
		log.info("查询到的系统用户信息 -> [{}]", sysUser);

		// 判断用户信息是否正确
		if (sysUser == null) {
			throw new AuthException(RsCodes.AUTH_ERROR, "账号或密码错误");
		}

		// SysUser 转换为 SysUserVO
		return BeanUtil.copyInstance(sysUser, SysUserVO.class);
	}
}
