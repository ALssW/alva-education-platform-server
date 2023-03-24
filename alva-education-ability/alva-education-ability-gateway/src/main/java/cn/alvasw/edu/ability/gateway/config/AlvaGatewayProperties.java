package cn.alvasw.edu.ability.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ALsW
 * @version 1.0.0
 * @date 2023-03-23
 */
@Component
@ConfigurationProperties("cn.alvasw.gateway")
public class AlvaGatewayProperties {

	private List<String> whitelist;

	public List<String> getWhitelist() {
		return whitelist;
	}

	public void setWhitelist(List<String> whitelist) {
		this.whitelist = whitelist;
	}
}
