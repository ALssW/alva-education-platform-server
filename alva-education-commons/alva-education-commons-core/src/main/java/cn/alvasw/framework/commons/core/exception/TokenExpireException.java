package cn.alvasw.framework.commons.core.exception;

import java.util.Map;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
public class TokenExpireException extends RuntimeException {
	private Map<String, Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public TokenExpireException(Map<String, Object> data) {
		this.data = data;
	}
}
