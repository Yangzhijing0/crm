package com.woniuxy.crm67;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.woniuxy.dao")
@ComponentScan(basePackages = {"com.woniuxy.service","com.woniuxy.controller","com.woniuxy.config","com.woniuxy.realm"})
public class Crm67Application {

	public static void main(String[] args) {
		SpringApplication.run(Crm67Application.class, args);
	}

}
