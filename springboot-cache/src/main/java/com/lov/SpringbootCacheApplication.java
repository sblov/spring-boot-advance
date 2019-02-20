package com.lov;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
 *	一、搭建基本环境
 *	1、导入数据库文件，创建department与employee表
 *	2、创建javabean封装数据
 *	3、整合mybatis操作数据库
 *		1、配置数据源信息
 *		2、使用注解版mybatis
 *	二、使用缓存
 *	1、开启基于注解的缓存 @EnableCaching
 *	2、标注缓存注解即可
 *		@Cacheable
 *		@CacheEvict
 *		@CachePut
 */
@EnableCaching
@MapperScan("com.lov.mapper")
@SpringBootApplication
public class SpringbootCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCacheApplication.class, args);
	}

}
