package cn.alvasw.edu.ability.gateway.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
public class ResponseUtil {

	public static Mono<Void> response(ServerWebExchange exchange, Object result) {
		ServerHttpResponse response = exchange.getResponse();
		response.getHeaders().set("Content-Type", "application/json;charset=utf-8");
		return response.writeWith(Mono.just(
				response.bufferFactory().wrap(
						JSON.toJSONString(result)
								.getBytes(StandardCharsets.UTF_8))));
	}
}
