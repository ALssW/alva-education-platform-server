package cn.alvasw.edu.data.auth.constant;


import java.util.HashMap;
import java.util.Map;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public enum FromType {

	/**
	 * PC端 系统平台
	 */
	SYS(0, "sys"),
	/**
	 * PC端 教师
	 */
	TEACHER(1, "tcr"),
	/**
	 * 小程序端
	 */
	PE_MINI(2, "wx");

	private static final Map<Integer, FromType> VALUE_MAP = new HashMap<>();

	static {
		for (FromType fromType : FromType.values()) {
			VALUE_MAP.put(fromType.getCode(), fromType);
		}
	}

	private final Integer code;
	private final String  type;

	FromType(Integer code, String type) {
		this.code = code;
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public static FromType valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}

	@Override
	public String toString() {
		return String.valueOf(code);
	}
}
