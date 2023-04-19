package cn.alvasw.framework.commons.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-19
 */
public class DateTimeUtil {

	private static final String            FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
	private static final DateTimeFormatter FORMAT        = DateTimeFormatter.ofPattern(FORMAT_STRING);

	public static String now() {
		return LocalDateTime.now().format(FORMAT);
	}

}
