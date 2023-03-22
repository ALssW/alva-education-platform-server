package cn.alvasw.framework.commons.mybatis.config;

import cn.alvasw.framework.commons.mybatis.meta.BassEntityMetaDateHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-21
 */
@Configuration
@MapperScan("cn.alvasw.**.dao")
@EnableTransactionManagement
public class MyBatisConfiguration {

	@Bean
	public BassEntityMetaDateHandler baseEntityMetaDateHandler(){
		return new BassEntityMetaDateHandler();
	}

}
