package cn.alvasw.framework.commons.mybatis.meta;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-21
 */
public class BassEntityMetaDateHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		metaObject.setValue("createTime", LocalDateTime.now());
		metaObject.setValue("updateTime", LocalDateTime.now());
		metaObject.setValue("status", 0);
		metaObject.setValue("delFlag", 0);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		metaObject.setValue("updateTime", LocalDateTime.now());
	}
}
