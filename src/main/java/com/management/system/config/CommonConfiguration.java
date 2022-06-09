package com.management.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.management.system.errors.ExceptionTranslator;

@Configuration
public class CommonConfiguration {
	
	@Bean()
	public ExceptionTranslator getExceptionTranslator() {
		return new ExceptionTranslator();
	}

}
