package cn.alvasw.framework.commons.base.exception;

import cn.alvasw.framework.commons.base.result.RsCodes;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
public class ServiceException extends RuntimeException {

	private RsCodes codes;
	private String  msg;

	public ServiceException() {
	}

	public ServiceException(RsCodes codes, String msg) {
		this.codes = codes;
		this.msg = msg;
	}

}
