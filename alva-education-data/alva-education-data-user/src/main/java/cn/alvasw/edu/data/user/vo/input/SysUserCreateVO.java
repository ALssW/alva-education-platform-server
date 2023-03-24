package cn.alvasw.edu.data.user.vo.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Data
public class SysUserCreateVO {

	@NotBlank(message = "系统账号不能为空")
	private String account;

	@NotBlank(message = "系统密码不能为空")

	private String password;

	@NotBlank(message = "系统用户名不能为空")
	private String name;

}
