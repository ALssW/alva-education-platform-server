package cn.alvasw.edu.data.auth.vo.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Data
public class LoginParamVO {

	@NotBlank(message = "账号不能为空")
	private String account;
	@NotBlank(message = "密码不能为空")
	private String password;

}
