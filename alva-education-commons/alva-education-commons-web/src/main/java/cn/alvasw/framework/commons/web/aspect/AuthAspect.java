package cn.alvasw.framework.commons.web.aspect;

import cn.alvasw.framework.commons.web.utils.AuthUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
@Aspect
@Component
public class AuthAspect {

	@Around("@annotation(cn.alvasw.framework.commons.web.annotation.Auth)")
	public Object authAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 获取请求
		ServletRequestAttributes atr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (atr == null) {
			return joinPoint.proceed();
		}
		AuthUtil.putUid(Long.parseLong(atr.getRequest().getHeader("UID")));
		try {
			return joinPoint.proceed();
		} finally {
			AuthUtil.removeUid();
		}

	}

}
