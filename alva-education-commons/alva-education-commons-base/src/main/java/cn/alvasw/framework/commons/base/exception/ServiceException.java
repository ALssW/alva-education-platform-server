package cn.alvasw.framework.commons.base.exception;

import cn.alvasw.framework.commons.base.result.RsCodes;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
public class ServiceException extends RuntimeException {

	private Integer code;
	private String  msg;

	public ServiceException() {
	}

	public ServiceException(RsCodes code, String msg) {
		this(code.getCode(), msg);
	}

	public ServiceException(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
