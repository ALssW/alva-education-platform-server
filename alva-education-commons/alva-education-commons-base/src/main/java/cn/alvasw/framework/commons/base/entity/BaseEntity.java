package cn.alvasw.framework.commons.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.sql.Timestamp;


/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-15
 */
public class BaseEntity {

	@TableField(fill = FieldFill.INSERT)
	private Timestamp createTime;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Timestamp updateTime;
	@TableField(fill = FieldFill.INSERT)
	private Integer   status;
	@TableField(fill = FieldFill.INSERT)
	private Integer   delFlag;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "BaseEntity{" +
				"createTime=" + createTime +
				", updateTime=" + updateTime +
				", status=" + status +
				", delFlag=" + delFlag +
				'}';
	}
}
