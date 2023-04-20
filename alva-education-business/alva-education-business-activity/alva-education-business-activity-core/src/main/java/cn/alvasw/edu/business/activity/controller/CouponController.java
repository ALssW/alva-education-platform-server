package cn.alvasw.edu.business.activity.controller;

import cn.alvasw.edu.business.activity.service.CouponService;
import cn.alvasw.edu.data.activity.entity.Coupon;
import cn.alvasw.framework.commons.base.result.Rs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-04-19
 */
@RestController
@RequestMapping("/activity/coupon")
@Slf4j
public class CouponController {

	@Resource
	private CouponService couponService;

	@RequestMapping("/list")
	public Rs<List<Coupon>> list(){
		log.info("[获取优惠券列表]");
		return Rs.assertEmpty(couponService.list());
	}

}
