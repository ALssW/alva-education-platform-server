package cn.alvasw.edu.business.notify.listener;

import cn.alvasw.edu.business.notify.utils.EmailUtil;
import cn.alvasw.edu.data.notify.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Component
@Slf4j
public class EmailMsgListener {

	@Value("${spring.mail.username}")
	private String from;

	@KafkaListener(topics = "topic-email")
	public void emailTopicHandler(Email email){
		log.info("[<通知>发送邮箱] email -> [{}]", email);
		email.setFrom(from);
		EmailUtil.sendMail(email);
	}

}
