package cn.alvasw.framework.commons.web.config;

import cn.alvasw.framework.commons.web.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@Configuration
public class BaseWebConfiguration {

	@Bean
	public GlobalExceptionHandler globalExceptionHandler(){
		return new GlobalExceptionHandler();
	}

}
