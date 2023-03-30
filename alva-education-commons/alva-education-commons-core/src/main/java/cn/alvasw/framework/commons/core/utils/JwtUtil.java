package cn.alvasw.framework.commons.core.utils;

import cn.alvasw.framework.commons.core.exception.TokenExpireException;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public class JwtUtil {

	private static final String SECRET_KEY = "what-are-you-fucking-thinking-about???";

	/**
	 * Auth Token 过期时间
	 */
	private static final long TTL              = 1000 * 60 * 10L;
	// private static final long TTL = 1000 * 60 * 60 * 2L;
	/**
	 * Auth Token 续签时效
	 */
	private static final long RENEW_TIME_LIMIT = 1000 * 60 * 20L;
	// private static final long RENEW_TIME_LIMIT = 1000 * 60 * 60 * 4L;

	/**
	 * 生成jwt令牌
	 */
	public static String createJwt(String key, Object value, String ip) {
		long                    now = System.currentTimeMillis();
		HashMap<String, Object> map = new HashMap<>(2);
		map.put(key, value);
		JwtBuilder jwtBuilder = Jwts.builder()
				//自定义令牌中的数据
				.setClaims(map)
				//设置签发时间
				.setIssuedAt(new Date(now))
				//设置加密算法以及密钥
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY + ip);
		if (TTL > 0) {
			//设置令牌的过期时间
			jwtBuilder.setExpiration(new Date(now + TTL));
		}
		return jwtBuilder.compact();
	}

	/**
	 * 解析jwt令牌
	 *
	 * @param jwtToken JWT Token
	 * @param ip       IP
	 * @param key      从 JWT Token 中获取的值
	 * @return value
	 */
	@SuppressWarnings("all")
	public static Map getMap(String jwtToken, String ip, String key) {
		try {
			return Jwts.parser().setSigningKey(SECRET_KEY + ip)
					.parseClaimsJws(jwtToken).getBody().get(key, LinkedHashMap.class);
		} catch (ExpiredJwtException e) {
			// Token 超时补签
			Date expiration = e.getClaims().getExpiration();
			Date now        = new Date();
			if (now.getTime() - expiration.getTime() > RENEW_TIME_LIMIT) {
				// 续签实效过期
				return null;
			}
			// 可以续签，抛出异常通知
			throw new TokenExpireException((Map<String, Object>) e.getClaims().get(key));
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean check(String authToken, String ip) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY + ip)
					.parseClaimsJws(authToken);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
