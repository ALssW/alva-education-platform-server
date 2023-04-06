package cn.alvasw.edu.business.user.feign;

import cn.alvasw.edu.data.user.entity.User;
import cn.alvasw.edu.data.user.vo.input.UserVO;
import cn.alvasw.framework.commons.base.result.Rs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-01
 */
@FeignClient(value = "server-user", contextId = "wx")
@RequestMapping("/user/wx")
public interface WxUserFeign {

	@RequestMapping("/login")
	Rs<User> login(@RequestBody UserVO userVO);

}
