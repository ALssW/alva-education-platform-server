package cn.alvasw.edu.entity.dto;

import cn.alvasw.edu.file.Number2DateConverter;
import com.alibaba.excel.annotation.ExcelProperty;

import java.sql.Timestamp;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
public class CourseDTO {

	private String name;

	@ExcelProperty(converter = Number2DateConverter.class)
	private Timestamp startTime;

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
		return "CourseDTO{" +
				"name='" + name + '\'' +
				", startTime=" + startTime +
				'}';
	}
}
