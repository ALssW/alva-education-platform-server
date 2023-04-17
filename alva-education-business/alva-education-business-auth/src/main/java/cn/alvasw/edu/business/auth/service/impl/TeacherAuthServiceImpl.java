package cn.alvasw.edu.business.auth.service.impl;

import cn.alvasw.edu.business.auth.exception.AuthException;
import cn.alvasw.edu.business.auth.service.IAuthService;
import cn.alvasw.edu.business.user.feign.TeacherFeign;
import cn.alvasw.edu.data.auth.base.BaseUser;
import cn.alvasw.edu.data.auth.vo.input.LoginParamVO;
import cn.alvasw.edu.data.auth.vo.output.TeacherVO;
import cn.alvasw.edu.data.user.entity.Teacher;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import cn.alvasw.framework.commons.core.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Slf4j
@Service("authService-tcr")
public class TeacherAuthServiceImpl implements IAuthService {

	@Resource
	private TeacherFeign teacherFeign;

	@Override
	public BaseUser authLogin(LoginParamVO paramVO) {
		Rs<Teacher> teacherRs = teacherFeign.login(paramVO.getEmail(), paramVO.getCode());
		if (Rs.isOk(teacherRs) == null) {
			throw new AuthException(RsCodes.AUTH_ERROR, teacherRs.getMsg());
		}
		
		return BeanUtil.copyInstance(teacherRs.getData(), TeacherVO.class);
	}
}
