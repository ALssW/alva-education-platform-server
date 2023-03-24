package cn.alvasw.edu.ability.gateway.filter;

import cn.alvasw.edu.ability.gateway.config.YetGatewayConfiguration;
import cn.alvasw.edu.ability.gateway.utils.NetworkUtil;
import cn.alvasw.edu.ability.gateway.utils.ResponseUtil;
import cn.alvasw.framework.commons.base.exception.ServiceException;
import cn.alvasw.framework.commons.core.exception.TokenExpireException;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import cn.alvasw.framework.commons.core.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
@Component
@Slf4j
public class AuthTokenGlobalFilter implements GlobalFilter {

	/**
	 * Gateway 配置类
	 */
	@Resource
	private YetGatewayConfiguration config;

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	@SuppressWarnings("all")
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 获取当前请求、IP、请求路径
		ServerHttpRequest request = exchange.getRequest();

		String ip   = NetworkUtil.getIpAddr(request);
		String path = request.getURI().getPath();
		log.info("[正在进行身份认证过滤] path -> [{}], ip -> [{}]", path, ip);

		// 判断请求路径是否为白名单
		for (String ignorePath : config.getWhitelist()) {
			if (pathMatcher.match(ignorePath, path)) {
				log.info("[该请求符合白名单规则]");
				return chain.filter(exchange);
			}
		}

		// 身份认证
		String authToken = request.getHeaders().getFirst("Auth-Token");
		if (StringUtils.isEmpty(authToken)) {
			log.warn("[该请求未携带 Auth Token]");
			return ResponseUtil.response(exchange, Rs.error(RsCodes.AUTH_ERROR, "Auth Token not found"));
		}
		// "user":{"data":{"id":xx,"fromType":"xx","name":"xx"},"fromType":x}
		String                           uid;
		Map<String, Map<String, String>> userMap;
		try {
			userMap = JwtUtil.getMap(authToken, ip, "user");
			if (userMap == null) {
				log.warn("Auth Token 为空或过期");
				return ResponseUtil.response(exchange, Rs.error("Auth Token 为空或过期"));
			}
			uid = String.valueOf(userMap.get("data").get("id"));
		} catch (TokenExpireException expire) {
			// Token 过期
			// 续签
			log.info("[Auth Token 续签] Token Data -> [{}]", expire.getData());
			return ResponseUtil.response(exchange, Rs.error(RsCodes.TOKEN_RENEW,
					JwtUtil.createJwt("user", expire.getData(), ip)));
		} catch (Exception e) {
			log.warn("Auth Token 解析异常", e);
			throw new ServiceException(RsCodes.AUTH_ERROR, "Auth Token 解析异常");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set("UID", uid);

		//创建一个新的请求
		ServerHttpRequest newRequest = request.mutate().uri(request.getURI()).build();
		//再将新的请求 包装成一个装设者对象
		newRequest = new ServerHttpRequestDecorator(newRequest) {
			@Override
			public HttpHeaders getHeaders() {
				return headers;
			}
		};

		return chain.filter(exchange.mutate().request(newRequest).build());
	}
}
