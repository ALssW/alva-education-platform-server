package cn.alvasw.framework.commons.core.listener;

import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
public class LogMdcListener implements GenericApplicationListener {

	@Override
	public boolean supportsEventType(ResolvableType resolvableType) {
		return resolvableType.isAssignableFrom(ApplicationEnvironmentPreparedEvent.class);
	}

	@Override
	@SuppressWarnings("ALL")
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		System.out.println("配置 Log 文件名称");
		ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) applicationEvent)
				.getEnvironment();
		System.out.println(environment.getProperty("spring.application.name"));
		// 设置每个微服务的日志名称
		MDC.put("appName",
				environment.getProperty("spring.application.name"));

	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 19;
	}
}
