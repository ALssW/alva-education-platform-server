package cn.alvasw.edu.business.notify.utils;

import cn.alvasw.edu.data.notify.entity.Email;
import cn.alvasw.framework.commons.core.utils.ApplicationUtil;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
public class EmailUtil {

	private static final JavaMailSender SENDER;

	static {
		SENDER = ApplicationUtil.getBean(JavaMailSender.class);
	}

	/**
	 * 发送邮件的工具方法
	 *
	 * @param email 邮件
	 */
	public static void sendMail(Email email) {

		//创建一封邮件对象
		MimeMessage mimeMessage = SENDER.createMimeMessage();
		try {
			//将邮件对象包装成一个包装类
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			//设置标题
			mimeMessageHelper.setSubject(email.getSubject());
			//发件方
			mimeMessageHelper.setFrom(email.getFrom());
			//接收方
			mimeMessageHelper.setTo(email.getTo());
			//内容
			mimeMessageHelper.setText(email.getContent(), true);
			//发送时间
			mimeMessageHelper.setSentDate(new Date());
			//处理附件
			if (email.getFile() != null) {
				mimeMessageHelper.addAttachment("附件的名字", email.getFile());
			}

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		//发送邮件
		SENDER.send(mimeMessage);
	}

}
