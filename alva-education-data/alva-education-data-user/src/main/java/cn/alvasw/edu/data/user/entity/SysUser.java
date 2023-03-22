package cn.alvasw.edu.data.user.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public class SysUser extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	private String username;
	private String password;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
