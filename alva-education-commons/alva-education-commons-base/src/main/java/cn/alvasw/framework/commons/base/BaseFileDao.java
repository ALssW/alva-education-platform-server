package cn.alvasw.framework.commons.base;

import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
public interface BaseFileDao<T> {

	/**
	 * 从文件读取实体后批量插入实体
	 *
	 * @param entityList 从文件中读取的实体类
	 * @return 插入结果
	 */
	int insertBatchesFromFile(List<T> entityList);

}
