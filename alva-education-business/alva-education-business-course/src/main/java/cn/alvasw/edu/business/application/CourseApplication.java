package cn.alvasw.edu.business.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.annotation.MultipartConfig;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
@SpringBootApplication
@MultipartConfig
@MapperScan("cn.alvasw.edu.dao")
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
