package cn.alvasw.edu.business.course.application;

import cn.alvasw.edu.commons.cache.lock.EnableRedisLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
@SpringBootApplication
@EnableRedisLock
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
