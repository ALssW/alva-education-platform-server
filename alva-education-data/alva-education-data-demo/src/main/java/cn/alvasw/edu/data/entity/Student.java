package cn.alvasw.edu.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-15
 */
public class Student {

	@TableId(type = IdType.AUTO)
	private Integer   id;
	private String    name;
	private String    password;
	private Integer   age;
	private Timestamp birthday;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", age=" + age +
				", birthday=" + birthday +
				'}';
	}
}
