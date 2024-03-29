package cn.alvasw.framework.commons.web.config;

import cn.alvasw.framework.commons.web.feign.FeignParamsInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-10
 */
@Configuration
@EnableFeignClients(basePackages = "cn.alvasw.**.feign", defaultConfiguration = FeignParamsInterceptor.class)
@ComponentScan("cn.alvasw.framework")
@Slf4j
public class BaseWebConfiguration {

	@SuppressWarnings("all")
	public HttpMessageConverter configureMessageConverters() {
		System.out.println("注册使用 Fastjson");
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig               config    = new FastJsonConfig();
		config.setSerializerFeatures(
				// 保留map空的字段
				// SerializerFeature.WriteMapNullValue,
				// 将String类型的null转成""
				SerializerFeature.WriteNullStringAsEmpty,
				// 将Number类型的null转成0
				SerializerFeature.WriteNullNumberAsZero,
				// 将List类型的null转成[]
				SerializerFeature.WriteNullListAsEmpty,
				// 将Boolean类型的null转成false
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullListAsEmpty,
				// 避免循环引用
				SerializerFeature.DisableCircularReferenceDetect);

		converter.setFastJsonConfig(config);
		converter.setDefaultCharset(StandardCharsets.UTF_8);
		List<MediaType> mediaTypeList = new ArrayList<>();
		// 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
		mediaTypeList.add(MediaType.APPLICATION_JSON);
		converter.setSupportedMediaTypes(mediaTypeList);
		return converter;
	}

	@Bean
	public RestTemplate restClient(){
		return new RestTemplate();
	}

}
