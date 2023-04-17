package cn.alvasw.edu.business.activity.service;

import cn.alvasw.edu.data.activity.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 针对表【activity(活动)】的数据库操作Service
 *
 * @author ALsW
 * @date 2023-04-17 14:38:39
 */
public interface ActivityService extends IService<Activity> {

	boolean updateStatus(Long id, Integer status);
}
