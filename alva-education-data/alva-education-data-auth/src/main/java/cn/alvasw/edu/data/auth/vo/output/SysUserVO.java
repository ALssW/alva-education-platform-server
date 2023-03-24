package cn.alvasw.edu.data.auth.vo.output;

import cn.alvasw.edu.data.auth.base.BaseUser;
import cn.alvasw.edu.data.auth.constant.FromType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Getter
@Setter
@ToString
public class SysUserVO extends BaseUser {

	private String name;

	public SysUserVO() {
		super(FromType.SYS);
	}
}
