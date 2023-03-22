package cn.alvasw.edu.business.service.impl;

import cn.alvasw.edu.business.dao.ICourseDao;
import cn.alvasw.edu.business.entity.Course;
import cn.alvasw.edu.business.exception.CourseException;
import cn.alvasw.edu.business.file.ExcelReadListener;
import cn.alvasw.edu.business.service.ICourseService;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.base.result.RsCodes;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
@Service
public class CourseServiceImpl extends ServiceImpl<ICourseDao, Course> implements ICourseService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private ICourseDao courseDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Rs<Void> addCourses(MultipartFile excel) {
		try {
			log.info("开始读取 Excel");
			EasyExcel.read(excel.getInputStream(), Course.class,
					new ExcelReadListener<>(courseDao))
					.excelType(ExcelTypeEnum.XLSX).sheet().doRead();
		} catch (IOException e) {
			throw new CourseException(RsCodes.ERROR, "文件读取失败");
		}
		log.info("文件读取完成");
		return Rs.ok("文件读取完成");
	}
}
