package cn.alvasw.edu.ability.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Gateway 配置类
 *
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-23
 */
@Data
@Component
@ConfigurationProperties("cn.alvasw.gateway")
@RefreshScope
public class YetGatewayConfiguration {

	private List<String> whitelist;

}
