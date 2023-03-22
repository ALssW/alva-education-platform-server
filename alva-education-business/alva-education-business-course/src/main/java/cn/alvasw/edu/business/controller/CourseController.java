package cn.alvasw.edu.business.controller;

import cn.alvasw.edu.business.entity.Course;
import cn.alvasw.edu.business.service.ICourseService;
import cn.alvasw.framework.commons.base.result.Rs;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@RestController
@RequestMapping("/course")
public class CourseController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private ICourseService courseService;

	@RequestMapping("/add/excel")
	public Rs<Void> dbExcel(MultipartFile file) {
		log.info("接收到 Excel 上传请求");
		if (file == null || file.isEmpty()) {
			log.warn("文件为空");
			return Rs.fail("文件为空");
		}
		log.info("文件类型: {}", file.getContentType());
		return courseService.addCourses(file);
	}

	@RequestMapping("/get/excel")
	public ResponseEntity<InputStreamResource> getCourseExcel(@RequestParam("ids[]") List<Integer> ids) throws IOException {
		List<Course> courseList = courseService.listByIds(ids);
		String       path       = "D:\\Codes\\QianFeng\\Study\\Chapter_4\\alva-education-platform\\alva-education-platform-server\\alva-education-business\\alva-education-business-course\\src\\main\\resources\\courses";
		String       excelName  = "course-" + System.currentTimeMillis() + ".xlsx";
		File         excel      = new File(path, excelName);

		if (!excel.exists()) {
			if (excel.createNewFile()) {
				EasyExcel.write(excel, Course.class).excelType(ExcelTypeEnum.XLSX).sheet().doWrite(courseList);
			}
		} else {
			EasyExcel.write(excel, Course.class).excelType(ExcelTypeEnum.XLSX).sheet().doWrite(courseList);
		}
		FileSystemResource file = new FileSystemResource(excel);

		// 设置响应头
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + excelName);

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(new InputStreamResource(file.getInputStream()));
	}

	@RequestMapping("/list")
	public Rs<List<Course>> list() {
		log.info("查询课程列表请求");
		return Rs.ok(courseService.list());
	}

}
