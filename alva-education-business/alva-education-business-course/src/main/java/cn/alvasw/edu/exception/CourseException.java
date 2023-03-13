package cn.alvasw.edu.exception;

import cn.alvasw.framework.commons.exception.ServiceException;
import cn.alvasw.framework.commons.result.RsCodes;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
public class CourseException extends ServiceException {

	public CourseException() {
		super();
	}

	public CourseException(RsCodes code, String msg) {
		super(code, msg);
	}

	public CourseException(Integer code, String msg) {
		super(code, msg);
	}
}
