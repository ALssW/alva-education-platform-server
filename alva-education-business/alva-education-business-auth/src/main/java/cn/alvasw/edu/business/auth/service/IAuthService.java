package cn.alvasw.edu.business.auth.service;

import cn.alvasw.edu.data.auth.base.BaseUser;
import cn.alvasw.edu.data.auth.vo.input.LoginParamVO;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public interface IAuthService {

	/**
	 * 分布式登录统一登录业务
	 *
	 * @param paramVO 统一登录参数
	 * @return 统一返回类型
	 */
	BaseUser authLogin(LoginParamVO paramVO);

}
