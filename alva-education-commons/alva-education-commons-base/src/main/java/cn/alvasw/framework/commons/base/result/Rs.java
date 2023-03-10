package cn.alvasw.framework.commons.base.result;

import java.io.Serializable;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
public class Rs<T> implements Serializable {
	private Integer code;
	private String  msg;
	private T       data;

	public Rs() {
	}

	public Rs(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	// ok ---------------------------------

	public static <T> Rs<T> ok() {
		return ok(RsCodes.OK, null);
	}

	public static <T> Rs<T> ok(String msg) {
		return ok(msg, null);
	}

	public static <T> Rs<T> ok(T data) {
		return ok(RsCodes.OK, data);
	}

	public static <T> Rs<T> ok(String msg, T data) {
		return ok(RsCodes.OK.getCode(), msg, data);
	}

	public static <T> Rs<T> ok(RsCodes code, T data) {
		return ok(code.getCode(), code.getMsg(), data);
	}

	public static <T> Rs<T> ok(Integer code, String msg, T data) {
		return new Rs<>(code, msg, data);
	}

	// fail ---------------------------------

	public static <T> Rs<T> fail() {
		return fail(RsCodes.FAIL);
	}

	public static <T> Rs<T> fail(String msg) {
		return fail(RsCodes.FAIL, msg);
	}

	public static <T> Rs<T> fail(T data) {
		return fail(RsCodes.FAIL, data);
	}

	public static <T> Rs<T> fail(RsCodes codes) {
		return fail(codes.getCode(), codes.getMsg(), null);
	}

	public static <T> Rs<T> fail(RsCodes codes, T data) {
		return fail(codes.getCode(), codes.getMsg(), data);
	}

	public static <T> Rs<T> fail(RsCodes codes, String msg) {
		return fail(codes.getCode(), msg, null);
	}

	public static <T> Rs<T> fail(RsCodes codes, String msg, T data) {
		return fail(codes.getCode(), msg, data);
	}

	public static <T> Rs<T> fail(Integer code, String msg, T data) {
		return new Rs<>(code, msg, data);
	}
	// unknown ---------------------------------

	public static <T> Rs<T> unknown() {
		return unknown(RsCodes.UNKNOWN);
	}

	public static <T> Rs<T> unknown(String msg) {
		return unknown(RsCodes.UNKNOWN, msg);
	}

	public static <T> Rs<T> unknown(T data) {
		return unknown(RsCodes.UNKNOWN, data);
	}

	public static <T> Rs<T> unknown(RsCodes codes) {
		return unknown(codes.getCode(), codes.getMsg(), null);
	}

	public static <T> Rs<T> unknown(RsCodes codes, T data) {
		return unknown(codes.getCode(), codes.getMsg(), data);
	}

	public static <T> Rs<T> unknown(RsCodes codes, String msg) {
		return unknown(codes.getCode(), msg, null);
	}

	public static <T> Rs<T> unknown(RsCodes codes, String msg, T data) {
		return unknown(codes.getCode(), msg, data);
	}

	public static <T> Rs<T> unknown(Integer code, String msg, T data) {
		return new Rs<>(code, msg, data);
	}

}
