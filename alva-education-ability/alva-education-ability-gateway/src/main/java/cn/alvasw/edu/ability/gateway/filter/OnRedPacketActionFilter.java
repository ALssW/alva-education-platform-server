package cn.alvasw.edu.ability.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-19
 */
public class OnRedPacketActionFilter extends AbstractGatewayFilterFactory<Object> implements GatewayFilter, Ordered {

	@Override
	public GatewayFilter apply(Object config) {
		return this;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return null;
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
