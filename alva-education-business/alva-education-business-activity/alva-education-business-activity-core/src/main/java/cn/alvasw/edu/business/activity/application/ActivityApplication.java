package cn.alvasw.edu.business.activity.application;

import cn.alvasw.edu.commons.cache.lock.EnableRedisLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@SpringBootApplication
@EnableRedisLock
public class ActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityApplication.class, args);
	}

}
