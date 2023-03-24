package cn.alvasw.edu.data.course.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
public class LongListTypeHandler implements TypeHandler<List<Long>> {

	@Override
	public void setParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, JSON.toJSONString(parameter));
	}

	@Override
	public List<Long> getResult(ResultSet rs, String columnName) throws SQLException {
		return getNullableResult(rs.getString(columnName));
	}

	@Override
	public List<Long> getResult(ResultSet rs, int columnIndex) throws SQLException {
		return getNullableResult(rs.getString(columnIndex));
	}

	@Override
	public List<Long> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getNullableResult(cs.getString(columnIndex));
	}

	public List<Long> getNullableResult(String result) {
		if (result == null) {
			return null;
		}
		return JSONArray.parseArray(result, Long.class);
	}
}
