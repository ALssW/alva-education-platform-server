package cn.alvasw.framework.commons.base.exception;

import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
public class ServiceException extends RuntimeException {

	private RsCodes codes;
	private String  msg;

	public RsCodes getCodes() {
		return codes;
	}

	public String getMsg() {
		return msg;
	}

	public ServiceException() {
	}

	public ServiceException(RsCodes codes) {
		this(codes, codes.getMsg());
	}

	public ServiceException(RsCodes codes, String msg) {
		super(msg);
		this.msg = msg;
		this.codes = codes;
	}

	@Override
	public String toString() {
		return "{" +
				"code=" + codes.getCode() +
				", msg='" + (msg != null ? msg : codes.getMsg()) + '\'' +
				'}';
	}
}
