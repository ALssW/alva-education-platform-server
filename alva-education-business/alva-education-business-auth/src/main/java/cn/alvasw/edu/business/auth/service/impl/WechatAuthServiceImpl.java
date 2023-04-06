package cn.alvasw.edu.business.auth.service.impl;

import cn.alvasw.edu.business.auth.exception.AuthException;
import cn.alvasw.edu.business.auth.service.IAuthService;
import cn.alvasw.edu.business.user.feign.WxUserFeign;
import cn.alvasw.edu.data.auth.base.BaseUser;
import cn.alvasw.edu.data.auth.vo.input.LoginParamVO;
import cn.alvasw.edu.data.auth.vo.output.WxUserVO;
import cn.alvasw.edu.data.user.entity.Teacher;
import cn.alvasw.edu.data.user.entity.User;
import cn.alvasw.edu.data.user.vo.input.UserVO;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import cn.alvasw.framework.commons.core.utils.BeanUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-01
 */
@Service("authService-wx")
public class WechatAuthServiceImpl implements IAuthService {

	@Resource
	private WxUserFeign wxUserFeign;

	@Resource(name = "restClient")
	private RestTemplate rest;

	@Value("${alvasw.wx.wxLoginUrl}")
	private String wxLoginUrl;
	@Value("${alvasw.wx.appid}")
	private String appid;
	@Value("${alvasw.wx.secret}")
	private String secret;
	@Value("${alvasw.wx.grantType}")
	private String grantType;


	@Override
	public BaseUser authLogin(LoginParamVO paramVO) {
		String url = wxLoginUrl + "?appid=" + appid + "&secret=" + secret + "&js_code=" + paramVO.getCode() + grantType;

		String result = rest.getForObject(url, String.class);
		UserVO userVO = JSON.parseObject(result, UserVO.class);
		if (userVO == null || userVO.getSessionKey() == null) {
			throw new AuthException(RsCodes.AUTH_ERROR, result);
		}
		userVO.setAvatar(paramVO.getAvatar());
		userVO.setNickname(paramVO.getNickname());

		Rs<User> teacherRs = wxUserFeign.login(userVO);
		if (Rs.isOk(teacherRs) == null) {
			throw new AuthException(RsCodes.AUTH_ERROR, teacherRs.getMsg());
		}

		return BeanUtil.copyInstance(teacherRs.getData(), WxUserVO.class);
	}
}
