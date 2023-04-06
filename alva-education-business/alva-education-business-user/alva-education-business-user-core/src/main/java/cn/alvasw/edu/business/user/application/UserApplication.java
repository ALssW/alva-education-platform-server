package cn.alvasw.edu.business.user.application;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public NewTopic emailTopic(){
		return new NewTopic("topic-email", 4, (short) 2);
	}

}
