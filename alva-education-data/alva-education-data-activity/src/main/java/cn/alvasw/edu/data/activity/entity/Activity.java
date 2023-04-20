package cn.alvasw.edu.data.activity.entity;

import cn.alvasw.framework.commons.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动
 *
 * @author ALsW
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "activity")
@Data
public class Activity extends BaseEntity implements Serializable {
	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	 * 活动标题
	 */
	private String title;

	/**
	 * 活动封面
	 */
	private String cover;

	/**
	 * 活动页面
	 */
	private String url;

	/**
	 * 活动介绍
	 */
	private String info;

	/**
	 * 活动类型(0-红包雨 1-...)
	 */
	private Integer type;

	/**
	 * 活动开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginTime;

	/**
	 * 活动结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
}