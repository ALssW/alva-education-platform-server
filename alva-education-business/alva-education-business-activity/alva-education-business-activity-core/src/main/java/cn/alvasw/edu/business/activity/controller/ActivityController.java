package cn.alvasw.edu.business.activity.controller;

import cn.alvasw.edu.business.activity.service.ActivityService;
import cn.alvasw.edu.data.activity.entity.Activity;
import cn.alvasw.framework.commons.base.result.Rs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@RestController
@RequestMapping("/activity")
@Slf4j
public class ActivityController {

	@Resource
	private ActivityService activityService;

	@RequestMapping("/create")
	public Rs<Void> create(Activity activity) {
		log.info("[创建活动] activity -> {}", activity);
		return Rs.assertBool(activityService.save(activity));
	}

	@RequestMapping("/list")
	public Rs<List<Activity>> list(){
		log.info("[查询活动列表]");
		return Rs.assertEmpty(activityService.list());
	}

	@RequestMapping("/status/update")
	public Rs<Void> statusUpdate(Long id, Integer status){
		log.info("[修改活动状态] id -> {}, status -> {}", id, status);
		return Rs.assertBool(activityService.updateStatus(id, status));
	}

}
