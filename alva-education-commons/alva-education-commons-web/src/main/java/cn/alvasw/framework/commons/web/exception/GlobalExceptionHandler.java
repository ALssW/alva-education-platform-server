package cn.alvasw.framework.commons.web.exception;

import cn.alvasw.framework.commons.base.exception.ServiceException;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@RestControllerAdvice(basePackages = "cn.alvasw")
public class GlobalExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public Rs<Object> globalException(Exception e) {
		log.error("发生未知异常", e);
		return Rs.unknown("发生未知异常: " + e.getMessage());
	}

	@ExceptionHandler(SQLException.class)
	public Rs<Object> sqlException(Exception e) {
		log.error("throw SQL Exception, 请联系管理员", e);
		return Rs.error(e.getMessage());
	}

	@ExceptionHandler(ArithmeticException.class)
	public Rs<Object> arithmeticException(Exception e) {
		log.error("throw SQL Exception, 请联系管理员", e);
		return Rs.error(e.getMessage());
	}

	@ExceptionHandler(ServiceException.class)
	public Rs<Object> serviceException(ServiceException e) {
		log.warn("发生业务异常", e);

		if (e.getMsg() == null) {
			return Rs.error(e.getCodes());
		}
		return Rs.error(e.getCodes(), e.getMsg());
	}

	@ExceptionHandler(BindException.class)
	public Rs<Object> bindExceptionHandler(BindException e) {
		List<ObjectError> errors = e.getAllErrors();
		List<String>      tipMsg = new ArrayList<>(errors.size());
		for (ObjectError error : errors) {
			String message = error.getDefaultMessage();
			tipMsg.add(message);
		}
		log.warn("发生参数绑定异常", e);
		return Rs.error(RsCodes.BIND_ERROR, tipMsg);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public Rs<Object> constraintViolationException(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> vs     = e.getConstraintViolations();
		List<String>                tipMsg = new ArrayList<>(vs.size());
		for (ConstraintViolation<?> violation : vs) {
			String message = violation.getMessage();
			tipMsg.add(message);
		}
		log.warn("发生参数绑定异常", e);
		return Rs.error(RsCodes.BIND_ERROR, tipMsg);
	}

}
