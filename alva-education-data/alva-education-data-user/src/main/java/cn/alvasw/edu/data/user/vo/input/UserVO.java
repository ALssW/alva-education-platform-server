package cn.alvasw.edu.data.user.vo.input;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-01
 */
@Data
@Accessors(chain = true)
public class UserVO {

	private String openid;
	@JSONField(name = "session_key")
	private String sessionKey;
	private String avatar;
	private String nickname;

}
