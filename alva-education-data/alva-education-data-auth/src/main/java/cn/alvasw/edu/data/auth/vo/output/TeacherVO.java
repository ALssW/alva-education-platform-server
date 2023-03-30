package cn.alvasw.edu.data.auth.vo.output;

import cn.alvasw.edu.data.auth.base.BaseUser;
import cn.alvasw.edu.data.auth.constant.FromType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Getter
@Setter
@ToString
public class TeacherVO extends BaseUser {

	private String name;
	private String email;

	public TeacherVO() {
		super(FromType.TEACHER);
	}
}
