package cn.alvasw.framework.commons.web.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public class NetworkUtil {

	public static final String LOCALHOST = "127.0.0.1";
	public static final String SPLIT = ",";
	public static final Integer IP_ADDRESS_LENGTH = 15;

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (LOCALHOST.equals(ipAddress)) {
					// 根据网卡取本机配置的IP
					InetAddress inet;
					try {
						inet = InetAddress.getLocalHost();
						ipAddress = inet.getHostAddress();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
				}
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			// "***.***.***.***".length()
			if (ipAddress != null && ipAddress.length() > IP_ADDRESS_LENGTH) {
				// = 15
				if (ipAddress.indexOf(SPLIT) > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			ipAddress = "";
		}

		return ipAddress;
	}


}
