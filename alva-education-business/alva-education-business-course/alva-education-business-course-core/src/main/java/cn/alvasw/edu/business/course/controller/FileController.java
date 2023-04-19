package cn.alvasw.edu.business.course.controller;

import cn.alvasw.framework.commons.base.result.Rs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-17
 */
@RestController
@MultipartConfig
@RequestMapping("/file")
@Slf4j
public class FileController {

	@Value("${alvasw.file.path}")
	private String savePath;

	@RequestMapping("/img/uploader")
	public Rs<String> uploaderImg(@RequestParam("file") MultipartFile file) {
		log.info("[上传课程图片] fileName -> [{}], fileSize -> [{}]", file.getOriginalFilename(), file.getSize());
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		String fileName = file.getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
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

	@RequestMapping("/img/download")
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


}
