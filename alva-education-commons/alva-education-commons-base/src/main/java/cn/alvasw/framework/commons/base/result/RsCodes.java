package cn.alvasw.framework.commons.base.result;

import java.util.HashMap;

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

	AUTH_ERROR(600, "验证错误"),
	TOKEN_RENEW(601, "Token 续签"),
	TOKEN_EXPIRE(602, "Token 过期"),

	ACTIVITY_NOT_FOUND(701, "活动不存在"),
	ACTIVITY_NOT_BEGIN(702, "活动未开始"),
	ACTIVITY_END(703, "活动已结束"),
	ACTIVITY_EMPTY(704, "活动红包已空");


	private static final HashMap<Integer, RsCodes> RS_MAP;

	static {
		RS_MAP = new HashMap<>(RsCodes.values().length);
		for (RsCodes rsCodes : RsCodes.values()) {
			RS_MAP.put(rsCodes.getCode(), rsCodes);
		}
	}


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

	public static RsCodes getValue(Integer code) {
		return RS_MAP.get(code);
	}

}
