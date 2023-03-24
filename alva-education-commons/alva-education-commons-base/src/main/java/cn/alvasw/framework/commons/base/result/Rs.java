package cn.alvasw.framework.commons.base.result;

import cn.alvasw.plugin.mybatis.entity.PageResult;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rs<T> extends PageResult implements Serializable {
	private Integer code;
	private String  msg;
	private T       data;

	private Rs() {
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

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> T isOk(Rs<T> rs) {
		if (RsCodes.OK.getCode().equals(rs.getCode()) && rs.getData() != null) {
			return rs.getData();
		}
		return null;
	}

	public static <T> Rs<T> assertBool(boolean success) {
		return assertBool(success, "操作成功", "操作失败");
	}

	public static <T> Rs<T> assertBool(boolean success, String okMsg, String failMsg) {
		return success ? Rs.ok(okMsg) : Rs.fail(failMsg);
	}

	public static <T> Rs<T> assertNull(T obj) {
		return assertNull(obj, "查询成功", "查询失败");
	}

	public static <T> Rs<T> assertNull(T obj, String okMsg, String failMsg) {
		return obj != null ? Rs.ok(okMsg, obj) : Rs.fail(RsCodes.NOT_FOUND, failMsg);
	}

	public static <T> Rs<List<T>> assertEmpty(List<T> coll) {
		return assertEmpty(coll, "查询成功", "查询失败");
	}

	public static <T> Rs<List<T>> assertEmpty(List<T> coll, String okMsg, String failMsg) {
		return coll != null && !coll.isEmpty() ? Rs.ok(okMsg, coll) : Rs.fail(RsCodes.NOT_FOUND, failMsg);
	}

	// ok ---------------------------------

	public static <T> Rs<T> ok() {
		return ok(RsCodes.OK);
	}

	public static <T> Rs<T> ok(String msg) {
		return ok(RsCodes.OK, msg);
	}

	public static <T> Rs<T> ok(T data) {
		return ok(RsCodes.OK, data);
	}

	public static <T> Rs<T> ok(String msg, T data) {
		return ok(RsCodes.OK, msg, data);
	}

	public static <T> Rs<T> ok(RsCodes codes) {
		return ok(codes, codes.getMsg(), null);
	}

	public static <T> Rs<T> ok(RsCodes codes, T data) {
		return ok(codes, codes.getMsg(), data);
	}

	public static <T> Rs<T> ok(RsCodes codes, String msg) {
		return ok(codes, msg, null);
	}

	public static <T> Rs<T> ok(RsCodes codes, String msg, T data) {
		return ok(codes.getCode(), msg, data);
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

	public static <T> Rs<T> fail(String msg, T data) {
		return fail(RsCodes.FAIL, msg, data);
	}

	public static <T> Rs<T> fail(RsCodes codes) {
		return fail(codes, codes.getMsg(), null);
	}

	public static <T> Rs<T> fail(RsCodes codes, T data) {
		return fail(codes, codes.getMsg(), data);
	}

	public static <T> Rs<T> fail(RsCodes codes, String msg) {
		return fail(codes, msg, null);
	}

	public static <T> Rs<T> fail(RsCodes codes, String msg, T data) {
		return fail(codes.getCode(), msg, data);
	}

	public static <T> Rs<T> fail(Integer code, String msg, T data) {
		return new Rs<>(code, msg, data);
	}

	// error ---------------------------------

	public static <T> Rs<T> error() {
		return error(RsCodes.ERROR);
	}

	public static <T> Rs<T> error(String msg) {
		return error(RsCodes.ERROR, msg);
	}

	public static <T> Rs<T> error(T data) {
		return error(RsCodes.ERROR, data);
	}

	public static <T> Rs<T> error(String msg, T data) {
		return Rs.error(RsCodes.ERROR, msg, data);
	}

	public static <T> Rs<T> error(RsCodes codes) {
		return error(codes, codes.getMsg(), null);
	}

	public static <T> Rs<T> error(RsCodes codes, T data) {
		return error(codes, codes.getMsg(), data);
	}

	public static <T> Rs<T> error(RsCodes codes, String msg) {
		return error(codes, msg, null);
	}

	public static <T> Rs<T> error(RsCodes codes, String msg, T data) {
		return error(codes.getCode(), msg, data);
	}

	public static <T> Rs<T> error(Integer code, String msg, T data) {
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
		return unknown(codes, codes.getMsg(), null);
	}

	public static <T> Rs<T> unknown(RsCodes codes, T data) {
		return unknown(codes, codes.getMsg(), data);
	}

	public static <T> Rs<T> unknown(RsCodes codes, String msg) {
		return unknown(codes, msg, null);
	}

	public static <T> Rs<T> unknown(RsCodes codes, String msg, T data) {
		return unknown(codes.getCode(), msg, data);
	}

	public static <T> Rs<T> unknown(Integer code, String msg, T data) {
		return new Rs<>(code, msg, data);
	}

}
