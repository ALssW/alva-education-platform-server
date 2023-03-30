package cn.alvasw.edu.business.auth.controller;

import cn.alvasw.edu.business.auth.service.IAuthService;
import cn.alvasw.edu.business.auth.exception.AuthException;
import cn.alvasw.edu.data.auth.base.BaseUser;
import cn.alvasw.edu.data.auth.constant.FromType;
import cn.alvasw.edu.data.auth.vo.input.LoginParamVO;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import cn.alvasw.framework.commons.core.utils.JwtUtil;
import cn.alvasw.framework.commons.web.utils.ApplicationUtil;
import cn.alvasw.framework.commons.web.utils.NetworkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	/**
	 * 分布登录统一接口
	 */
	@RequestMapping("/login")
	public Rs<String> authLogin(LoginParamVO paramVO,
	                            @RequestHeader(required = false)
	                            @NotNull(message = "登录类型不能为空")
			                            Integer fromType, HttpServletRequest request)
			throws AuthException {
		log.info("接收到分布式登录请求 - loginParam -> [{}], fromType -> [{}]", paramVO, fromType);

		// 获取分布式平台对应的登录类型
		FromType from = FromType.valueOf(fromType);
		if (from == null) {
			throw new AuthException(RsCodes.AUTH_ERROR, "登录类型错误");
		}

		// 对应登录类型的登录验证业务类
		IAuthService authService = ApplicationUtil.getBean("authService-" + from.getType(), IAuthService.class);
		BaseUser     baseUser    = authService.authLogin(paramVO);
		log.debug("获取的平台登录用户 baseUser -> [{}]", baseUser);

		// 生成 JWT 令牌
		String ip = NetworkUtil.getIpAddr(request);
		log.debug("JWT Token 加盐, ip -> [{}]", ip);
		HashMap<String, Object> userMap = new HashMap<>(2);
		userMap.put("data", baseUser);
		userMap.put("fromType", baseUser.getFromType().getCode());

		return Rs.ok("登录成功", JwtUtil.createJwt("user", userMap, ip));
	}

	@RequestMapping("/check")
	public Rs<Void> check(String authToken, HttpServletRequest request) {
		return Rs.assertBool(
				JwtUtil.check(authToken, NetworkUtil.getIpAddr(request))
				,"Token 验证成功", "Token 验证失败");
	}

}
