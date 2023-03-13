package cn.alvasw.framework.commons.web.config;

import cn.alvasw.framework.commons.web.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@Configuration
public class BaseWebConfiguration {

	@Bean
	public GlobalExceptionHandler globalExceptionHandler() {
		System.out.println("注册 GlobalExceptionHandler");
		return new GlobalExceptionHandler();
	}

	@Bean
	public CorsFilter corsFilter() {
		System.out.println("注册 CorsFilter");
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("*"));
		configuration.setAllowedMethods(Collections.singletonList("*"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}

}
