package cn.alvasw.edu.controller;

import cn.alvasw.framework.commons.base.result.Rs;
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

	@RequestMapping("/test")
	public Rs<Object> test(){
		// System.out.println(1/0);
		return Rs.ok();
	}

}
