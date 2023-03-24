package cn.alvasw.edu.data.user.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysUser extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
	private Long   id;
	private String account;
	private String password;
	private String name;

}
