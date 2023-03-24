package cn.alvasw.edu.ability.gateway.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public class NetworkUtil {

	public static final String LOCALHOST = "127.0.0.1";
	public static final String UNKNOWN = "unknown";
	public static final Integer IP_ADDRESS_LENGTH = 15;

	public static String getIpAddr(ServerHttpRequest request) {
		HttpHeaders headers   = request.getHeaders();
		String      ipAddress = headers.getFirst("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = headers.getFirst("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = headers.getFirst("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = Optional.ofNullable(request.getRemoteAddress())
					.map(address -> address.getAddress().getHostAddress())
					.orElse("");
			if (LOCALHOST.equals(ipAddress)) {
				// 根据网卡取本机配置的IP
				try {
					InetAddress inet = InetAddress.getLocalHost();
					ipAddress = inet.getHostAddress();
				} catch (UnknownHostException e) {
					// ignore
				}
			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > IP_ADDRESS_LENGTH) {
			int index = ipAddress.indexOf(",");
			if (index > 0) {
				ipAddress = ipAddress.substring(0, index);
			}
		}
		return ipAddress;
	}


}
