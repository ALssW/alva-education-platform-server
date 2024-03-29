package cn.alvasw.edu.data.course.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class Course extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
	private Long          id;
	private Long          typeId;
	private Long          teacherId;
	private String        name;
	private String        image;
	private String        info;
	private BigDecimal    price;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

}
