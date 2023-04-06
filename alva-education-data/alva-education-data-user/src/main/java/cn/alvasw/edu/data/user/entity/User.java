package cn.alvasw.edu.data.user.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	private String openid;
	private String sessionKey;
	private String avatar;
	private String nickname;

}
