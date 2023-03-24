package cn.alvasw.framework.commons.web.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
public class FeignParamsInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		// 将请求中的 UID 传递
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs == null) {
			return;
		}
		String uid = attrs.getRequest().getHeader("UID");
		if (uid != null) {
			template.header("UID", uid);
		}

	}
}
