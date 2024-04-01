package com.luanvan.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtProperties {
	
	@Value("${security.jwt}")
	private String secretKey;

	public String getSecretKey() {
		return secretKey;
	}
	
}
