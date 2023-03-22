package cn.alvasw.framework.commons.web.exception;

import cn.alvasw.framework.commons.base.exception.ServiceException;
import cn.alvasw.framework.commons.base.result.Rs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public Rs<Object> globalException(Exception e) {
		log.error("发生未知异常", e);
		return Rs.unknown(e.getMessage());
	}

	@ExceptionHandler(ServiceException.class)
	public Object serviceException(ServiceException e) {
		log.warn("发生业务异常", e);
		return Rs.fail("业务异常");
	}

}
