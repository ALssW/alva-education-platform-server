package cn.alvasw.framework.commons.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Component
public class ApplicationUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static <T> T getBean(Class<T> cls) {
		return applicationContext.getBean(cls);
	}

	public static <T> T getBean(String beanName, Class<T> cls) {
		return cls.cast(getBean(beanName));
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	@Override
	@SuppressWarnings("all")
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationUtil.applicationContext = applicationContext;
	}

}
