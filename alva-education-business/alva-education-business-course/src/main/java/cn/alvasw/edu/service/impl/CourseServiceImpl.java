package cn.alvasw.edu.service.impl;

import cn.alvasw.edu.controller.CourseController;
import cn.alvasw.edu.entity.Course;
import cn.alvasw.edu.dao.ICourseDao;
import cn.alvasw.edu.entity.dto.CourseDTO;
import cn.alvasw.edu.exception.CourseException;
import cn.alvasw.edu.file.ExcelReadListener;
import cn.alvasw.edu.service.ICourseService;
import cn.alvasw.framework.commons.result.Rs;
import cn.alvasw.framework.commons.result.RsCodes;
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
