package cn.alvasw.edu.business.activity.controller;

import cn.alvasw.edu.business.activity.service.ActivityRedPacketService;
import cn.alvasw.edu.data.activity.vo.input.ActivityRedPacketContentVO;
import cn.alvasw.framework.commons.base.result.Rs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@RestController
@RequestMapping("/activity/red-packet")
@Slf4j
public class ActivityRedPacketController {

	@Resource
	private ActivityRedPacketService activityRedPacketService;

	@RequestMapping("/create")
	public Rs<Void> create(ActivityRedPacketContentVO activityRedPacketContentVO){
		log.info("[创建活动红包内容请求] vo -> {}", activityRedPacketContentVO);
		return Rs.assertBool(activityRedPacketService.saveContent(activityRedPacketContentVO));
	}

}
