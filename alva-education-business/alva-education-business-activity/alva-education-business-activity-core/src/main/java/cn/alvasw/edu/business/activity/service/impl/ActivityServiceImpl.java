package cn.alvasw.edu.business.activity.service.impl;

import cn.alvasw.edu.business.activity.dao.ActivityMapper;
import cn.alvasw.edu.business.activity.service.ActivityService;
import cn.alvasw.edu.data.activity.entity.Activity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【activity(活动)】的数据库操作Service实现
 *
 * @author ALsW
 * @date 2023-04-17 14:38:39
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
		implements ActivityService {

	@Override
	public boolean updateStatus(Long id, Integer status) {
		return false;

	}
}




