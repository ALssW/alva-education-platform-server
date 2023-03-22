package cn.alvasw.edu.business.file;

import cn.alvasw.framework.commons.base.dao.BaseFileDao;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
public class ExcelReadListener<T> implements ReadListener<T> {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final int BATCH_COUNT = 5;

	private BaseFileDao<T> fileDao;
	private List<T>        entityList = new ArrayList<>(BATCH_COUNT);

	public ExcelReadListener() {
	}

	public ExcelReadListener(BaseFileDao<T> fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public void invoke(T t, AnalysisContext analysisContext) {
		entityList.add(t);
		log.info("读取到一个实体 :{}", t);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		log.info("插入实体列表 List :{}", entityList);
		fileDao.insertBatchesFromFile(entityList);
	}
}
