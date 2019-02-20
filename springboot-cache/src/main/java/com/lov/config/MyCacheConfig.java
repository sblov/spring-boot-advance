package com.lov.config;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCacheConfig {

	@Bean("myKeyGenerator")
	public KeyGenerator keyGenerator() {
		return ( target,  method, params)->{
			return method.getName()+"{"+params[0].toString()+"}";
			};
	}
	
	
}
