package cn.alvasw.framework.commons.base.result;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
public enum RsCodes {
	/**
	 * 响应成功
	 */
	OK(200, "成功"),
	FAIL(300, "失败"),
	NOT_FOUND(404, "未找到该资源"),
	ERROR(500, "错误"),
	UNKNOWN(501, "未知错误"),
	BIND_ERROR(502, "参数绑定错误"),
	AUTH_ERROR(510, "验证错误");

	/**
	 * 代码
	 */
	private final Integer code;
	/**
	 * 信息
	 */
	private final String  msg;

	RsCodes(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
