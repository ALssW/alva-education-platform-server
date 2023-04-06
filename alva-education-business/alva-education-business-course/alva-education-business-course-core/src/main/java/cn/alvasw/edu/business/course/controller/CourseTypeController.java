package cn.alvasw.edu.business.course.controller;

import cn.alvasw.edu.business.course.service.ICourseTypeService;
import cn.alvasw.edu.data.course.entity.CourseType;
import cn.alvasw.edu.data.course.vo.input.CourseTypeCreateVO;
import cn.alvasw.edu.data.course.vo.output.CourseTypeTreeVO;
import cn.alvasw.framework.commons.base.result.Rs;
import cn.alvasw.framework.commons.core.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-24
 */
@RestController
@RequestMapping("/course/type")
@Slf4j
public class CourseTypeController {

	@Resource
	private ICourseTypeService courseTypeService;


	@RequestMapping("/list")
	public Rs<List<CourseTypeTreeVO>> list() {
		log.info("[接收到获取课程分类列表请求]");
		List<CourseType> courseTypeList = courseTypeService.listDesc();
		log.debug("[查询课程列表结果] courseTypeList -> [{}]", courseTypeList);

		// 转换课程分类列表
		Map<Long, CourseTypeTreeVO> treeVoMap        = new HashMap<>(courseTypeList.size());
		List<CourseTypeTreeVO>      parentTreeVoList = new ArrayList<>(courseTypeList.size());

		for (CourseType courseType : courseTypeList) {
			CourseTypeTreeVO treeVO = new CourseTypeTreeVO()
					.setId(courseType.getId())
					.setPid(courseType.getPid())
					.setLabel(courseType.getName())
					.setType(courseType.getType());

			if (treeVO.getPid() == null) {
				parentTreeVoList.add(treeVO);
			} else {
				treeVoMap.get(treeVO.getPid()).addChildren(treeVO);
			}
			treeVoMap.put(treeVO.getId(), treeVO);
		}

		return Rs.assertEmpty(parentTreeVoList);
	}

	@RequestMapping("/create")
	public Rs<CourseType> create(@Validated CourseTypeCreateVO courseTypeCreateVO) {
		log.info("[接收到创建分类请求] courseTypeCreateVO -> [{}]", courseTypeCreateVO);
		return Rs.assertBool(courseTypeService.save(BeanUtil.copyInstance(courseTypeCreateVO, CourseType.class)));
	}


}
