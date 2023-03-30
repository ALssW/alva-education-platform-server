package cn.alvasw.edu.data.user.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class Teacher extends BaseEntity {

	private Long    id;
	private String  email;
	private String  name;
	private Integer age;
	private String  info;

}
