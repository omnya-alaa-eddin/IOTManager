package com.device.manager.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties( prefix ="aws")
@Component
@Getter
@Setter
public class AppConfig {

private String accessKeyId;
private String secretKeyId;
private String clientEndpoint;
	
	
}
