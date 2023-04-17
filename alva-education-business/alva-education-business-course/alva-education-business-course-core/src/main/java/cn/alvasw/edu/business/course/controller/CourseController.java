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
@MultipartConfig
@RequestMapping("/course")
@Slf4j
public class CourseController {

	@Resource
	private ICourseService courseService;

	@Value("${alvasw.file.path}")
	private String savePath;

	@RequestMapping(value = "/file/img/uploader")
	public Rs<String> upImg(@RequestParam("file") MultipartFile file) {
		log.info("[上传课程图片] fileName -> [{}], fileSize -> [{}]", file.getOriginalFilename(), file.getSize());
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		String fileName = file.getOriginalFilename();
		String fileType = fileName != null ? fileName.substring(fileName.lastIndexOf(".")) : ".png";
		String filename = UUID.randomUUID() + fileType;

		try (
				InputStream in = file.getInputStream();
				FileOutputStream out = new FileOutputStream(new File(saveFile, filename))
		) {
			IOUtils.copy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
			Rs.fail("上传失败");
		}

		return Rs.ok("上传成功", filename);
	}

	@RequestMapping("/file/img/download")
	public void downloadImg(String fileName, HttpServletResponse response) {
		log.info("[下载课程图片] fileName -> [{}]", fileName);
		File file = new File(savePath, fileName);
		if (!file.exists()) {
			return;
		}

		response.setContentType(MediaType.IMAGE_JPEG_VALUE + ";" + MediaType.IMAGE_PNG_VALUE);
		try (
				InputStream in = new FileInputStream(file);
				OutputStream out = response.getOutputStream()
		) {
			IOUtils.copy(in, out);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


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
