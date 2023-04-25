package cn.alvasw.edu.business.activity.dao;

import cn.alvasw.edu.data.activity.bo.ActivityBO;
import cn.alvasw.edu.data.activity.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 针对表【activity(活动)】的数据库操作Mapper
 *
 * @author ALsW
 * @date 2023-04-17 14:38:39
 */
public interface ActivityDao extends BaseMapper<Activity> {

	ActivityBO queryInfoByActId(@Param("activityId") Long activityId);

}




