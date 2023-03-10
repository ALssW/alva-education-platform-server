package cn.alvasw.framework.commons.web.exception;

import cn.alvasw.framework.commons.base.exception.ServiceException;
import cn.alvasw.framework.commons.base.result.Rs;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public Rs<Object> globalException(Exception e){
		return Rs.unknown();
	}

	@ExceptionHandler(ServiceException.class)
	public Object serviceException(){
		return Rs.fail("业务异常");
	}

}
