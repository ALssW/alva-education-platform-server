package cn.alvasw.edu.data.user.vo.output;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
@Data
public class SysUserQueryVO {

	private Long   id;
	private String account;
	private String name;

}
