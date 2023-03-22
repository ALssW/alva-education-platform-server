package cn.alvasw.edu.business.entity;

import cn.alvasw.edu.business.file.Number2DateConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.sql.Timestamp;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
public class Course {

	@ExcelIgnore
	private Integer   id;
	private String    name;
	@ExcelProperty(converter = Number2DateConverter.class)
	private Timestamp startTime;

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

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", name='" + name + '\'' +
				", startTime=" + startTime +
				'}';
	}
}
