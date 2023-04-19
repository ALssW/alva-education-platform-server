package cn.alvasw.edu.business.course.controller;

import cn.alvasw.edu.business.course.service.ICourseService;
import cn.alvasw.edu.data.course.entity.Course;
import cn.alvasw.edu.data.course.vo.input.CourseCreateVO;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.core.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-27
 */
@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

	@Resource
	private ICourseService courseService;

	@RequestMapping("/create")
	public Rs<Void> create(@Validated CourseCreateVO courseVO) {
		log.info("[创建课程] -> [{}]", courseVO);
		return Rs.assertBool(courseService.save(
				BeanUtil.copyInstance(courseVO, Course.class)));
	}

	@RequestMapping("/list/tcr")
	public Rs<List<Course>> listByTcrId() {
		return Rs.assertEmpty(courseService.listByTcrId());
	}

	@RequestMapping("/list")
	public Rs<List<Course>> list(){
		return Rs.assertEmpty(courseService.list());
	}

}
