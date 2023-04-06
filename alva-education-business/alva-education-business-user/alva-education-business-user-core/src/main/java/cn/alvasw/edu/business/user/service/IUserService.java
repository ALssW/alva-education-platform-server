package cn.alvasw.edu.business.user.service;

import cn.alvasw.edu.data.user.entity.User;
import cn.alvasw.edu.data.user.vo.input.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-01
 */
public interface IUserService extends IService<User> {

	User findOrCreateByEmail(UserVO userVO);

}
