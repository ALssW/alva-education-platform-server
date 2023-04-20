package cn.alvasw.edu.business.activity.service.impl;

import cn.alvasw.edu.business.activity.dao.ActivityDao;
import cn.alvasw.edu.business.activity.service.ActivityService;
import cn.alvasw.edu.data.activity.entity.Activity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 针对表【activity(活动)】的数据库操作Service实现
 *
 * @author ALsW
 * @date 2023-04-17 14:38:39
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, Activity>
		implements ActivityService {

	@Override
	public boolean updateStatus(Long id, Integer status) {
		return this.update().set("status", status)
				.set("update_time", new Date())
				.eq("id", id)
				.update();
	}
}




