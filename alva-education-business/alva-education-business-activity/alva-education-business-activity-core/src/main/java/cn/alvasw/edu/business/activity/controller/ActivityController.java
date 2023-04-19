package cn.alvasw.edu.business.activity.controller;

import cn.alvasw.edu.business.activity.service.ActivityService;
import cn.alvasw.edu.data.activity.entity.Activity;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.core.utils.DateTimeUtil;
import com.mysql.cj.util.TimeUtil;
import io.micrometer.core.instrument.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.avatica.util.DateTimeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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

	@RequestMapping("/get/id")
	public Rs<Activity> getById(Long id){
		log.info("[获取活动] id -> {}", id);
		return Rs.assertNull(activityService.getById(id));
	}

	@RequestMapping("/server/time")
	public Rs<String> getServerTime(){
		String now = DateTimeUtil.now();
		log.info("[获取服务器时间] now -> {}", now);
		return Rs.assertNull(now);
	}

}
