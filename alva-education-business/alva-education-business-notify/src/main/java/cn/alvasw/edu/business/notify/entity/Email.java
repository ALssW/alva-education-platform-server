package cn.alvasw.edu.business.notify.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Data
@Accessors(chain = true)
public class Email {

	private String subject;
	private String from;
	private String to;
	private String content;
	private File   file;

}
