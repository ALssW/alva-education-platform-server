package cn.alvasw.framework.commons.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public class JwtUtil {

	private static final String SECRET_KEY = "what-are-you-fucking-thinking-about???";

	private static final long TTL = 1000 * 60 * 60 * 2L;

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
	 * @param key      从 JWT Token 中获取的值
	 * @param ip       IP
	 * @return value
	 */
	public static Object get(String jwtToken, String ip, String key) {
		try {
			return Jwts.parser().setSigningKey(SECRET_KEY + ip)
					.parseClaimsJws(jwtToken).getBody().get(key);
		} catch (ExpiredJwtException e) {
			//令牌超时
			Claims claims = e.getClaims();
			//补签
			return null;
		} catch (Exception e) {
			return null;
		}
	}


}
