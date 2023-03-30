package cn.alvasw.edu.business.user.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
public class LocalCache {

	private static final Map<String, Object> CACHE = new ConcurrentHashMap<>(8);

	public static Object putCache(String key, Object value) {
		return CACHE.put(key, value);
	}

	public static Object getCache(String key) {
		return CACHE.get(key);
	}

	public static Object remove(String key) {
		return CACHE.remove(key);
	}

}
