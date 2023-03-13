package cn.alvasw.edu.dao;

import cn.alvasw.edu.entity.Course;
import cn.alvasw.framework.commons.base.BaseFileDao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
@Mapper
public interface ICourseDao extends BaseMapper<Course>, BaseFileDao<Course> {

	/**
	 * 从文件读取课程后批量插入
	 *
	 * @param courseList 从文件中读取的课程列表
	 * @return 插入结果
	 */
	@Override
	int insertBatchesFromFile(@Param("courseList") List<Course> courseList);
}
