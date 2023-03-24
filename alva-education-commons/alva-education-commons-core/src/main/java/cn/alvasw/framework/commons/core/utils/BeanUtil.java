package cn.alvasw.framework.commons.core.utils;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于 Spring BeanUtils 自封装
 *
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-22
 */
public class BeanUtil extends BeanUtils {

	/**
	 * 将一个实例化类的属性复制给另一个类
	 */
	public static <T> T copyInstance(Object source, Class<T> targetCls) {
		try {
			T instance = instantiateClass(targetCls);
			copyProperties(source, instance);
			return instance;
		} catch (BeanInstantiationException e) {
			return null;
		}
	}

	public static <T> List<T> copyListInstance(List<?> sourceList, Class<T> targetCls) {
		List<T> instanceList = new ArrayList<>(sourceList.size());
		for (Object source : sourceList) {
			instanceList.add(copyInstance(source, targetCls));
		}
		return instanceList;
	}
}
