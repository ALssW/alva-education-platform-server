package cn.alvasw.edu.business.user.dao;

import cn.alvasw.edu.data.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-01
 */
@Repository
public interface UserDao extends BaseMapper<User> {
}
