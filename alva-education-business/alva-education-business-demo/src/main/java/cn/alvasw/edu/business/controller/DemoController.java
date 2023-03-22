package cn.alvasw.edu.business.controller;

import cn.alvasw.framework.commons.base.result.Rs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	private final Logger log = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping("/test")
	public Rs<Object> test() {
		log.info("接收到请求");
		log.warn("接收到请求");
		log.error("接收到请求");
		log.debug("接收到请求");
		log.trace("接收到请求");
		// System.out.println(1 / 0);
		return Rs.ok();
	}

}
