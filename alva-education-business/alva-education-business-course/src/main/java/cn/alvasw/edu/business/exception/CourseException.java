package cn.alvasw.edu.business.exception;

import cn.alvasw.framework.commons.base.exception.ServiceException;
import cn.alvasw.framework.commons.base.result.RsCodes;

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

}
