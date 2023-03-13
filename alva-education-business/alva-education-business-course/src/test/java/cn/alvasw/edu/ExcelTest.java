package cn.alvasw.edu;

import cn.alvasw.edu.application.CourseApplication;
import cn.alvasw.edu.dao.ICourseDao;
import cn.alvasw.edu.entity.Course;
import cn.alvasw.edu.entity.dto.CourseDTO;
import cn.alvasw.edu.file.ExcelReadListener;
import cn.alvasw.edu.service.ICourseService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourseApplication.class)
public class ExcelTest {

	@Resource
	private ICourseService courseService;

	@Test
	public void readExcelTest() {
		File excel = new File("D:\\Codes\\QianFeng\\Study\\Chapter_4\\alva-education-platform\\alva-education-platform-server\\alva-education-business\\alva-education-business-course\\src\\main\\resources\\course.xlsx");
		try {
			EasyExcel.read(new FileInputStream(excel), CourseDTO.class,
					new ExcelReadListener<CourseDTO>()).excelType(ExcelTypeEnum.XLSX).sheet().doRead();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void writeExcelTest() throws IOException {
		List<Course> list  = courseService.list();
		File         excel = new File("D:\\Codes\\QianFeng\\Study\\Chapter_4\\alva-education-platform\\alva-education-platform-server\\alva-education-business\\alva-education-business-course\\src\\main\\resources\\courses\\course-" + System.currentTimeMillis() + ".xlsx");
		if (!excel.exists()){
			excel.createNewFile();
		}
		EasyExcel.write(excel, Course.class).excelType(ExcelTypeEnum.XLSX).sheet().doWrite(list);

	}

}
