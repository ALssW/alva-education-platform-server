package cn.alvasw.edu.data.auth.base;

import cn.alvasw.edu.data.auth.constant.FromType;
import lombok.Data;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Data
public class BaseUser {

	private Long     id;
	private FromType fromType;

	public BaseUser(FromType fromType) {
		this.fromType = fromType;
	}
}
