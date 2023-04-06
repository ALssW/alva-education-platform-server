package cn.alvasw.edu.business.user.controller;

import cn.alvasw.edu.business.user.utils.LocalCache;
import cn.alvasw.edu.data.notify.entity.Email;
import cn.alvasw.framework.commons.base.result.Rs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@RestController
@RequestMapping("/user/code")
@Validated
@Slf4j
public class CodeController {

	@Resource
	private KafkaTemplate<String, Object> kafkaClient;

	@RequestMapping("/email")
	public Rs<String> emailCodec(@NotBlank(message = "邮箱不能为空") String email) {
		String code = String.valueOf(Math.round(Math.random() * 9000 + 1000));
		log.info("[生成邮箱验证码] email -> [{}], code -> [{}]", email, code);
		LocalCache.putCache(email, code);
		Email newEmail = new Email();
		newEmail.setSubject("ALvA 在线教育平台登录")
				.setTo(email)
				.setContent("[验证码] 正在进行登录操作，本次登录验证码为: " + code + ", 如非本人操作请忽略。");
		kafkaClient.send("topic-email", newEmail);
		return Rs.ok("邮件发送成功", code);
	}

}
