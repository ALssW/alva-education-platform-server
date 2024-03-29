package cn.alvasw.edu.business.user.dao;

import cn.alvasw.edu.data.user.entity.SysUser;
import cn.alvasw.edu.data.user.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Repository
public interface TeacherDao extends BaseMapper<Teacher> {
}
