package cn.alvasw.edu.business.user.service.impl;

import cn.alvasw.edu.business.user.dao.UserDao;
import cn.alvasw.edu.business.user.service.IUserService;
import cn.alvasw.edu.data.user.entity.User;
import cn.alvasw.edu.data.user.vo.input.UserVO;
import cn.alvasw.framework.commons.core.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

	@Resource
	private UserDao userDao;

	@Override
	public User findOrCreateByEmail(UserVO userVO) {
		User user = query().eq("openid", userVO.getOpenid()).one();
		if (user != null) {
			return user;
		}

		user = BeanUtil.copyInstance(userVO, User.class);
		return super.save(user) ? user : null;
	}
}
