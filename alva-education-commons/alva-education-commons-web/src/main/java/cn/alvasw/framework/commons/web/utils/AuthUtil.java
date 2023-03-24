package cn.alvasw.framework.commons.web.utils;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
public class AuthUtil {

	private static final ThreadLocal<Long> UID_LOCAL = new ThreadLocal<>();

	public static void putUid(Long uid) {
		UID_LOCAL.set(uid);
	}

	public static Long getUid() {
		return UID_LOCAL.get();
	}

	public static void removeUid() {
		UID_LOCAL.remove();
	}

	public static Long getRemoveUid() {
		try {
			return getUid();
		} finally {
			removeUid();
		}
	}

}
