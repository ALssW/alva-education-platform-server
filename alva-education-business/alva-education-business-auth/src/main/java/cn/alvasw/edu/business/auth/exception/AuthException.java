package cn.alvasw.edu.business.auth.exception;

import cn.alvasw.framework.commons.base.exception.ServiceException;
import cn.alvasw.framework.commons.base.result.RsCodes;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public class AuthException extends ServiceException {

	public AuthException(RsCodes codes) {
		super(codes);
	}

	public AuthException(RsCodes codes, String msg) {
		super(codes, msg);
	}
}
