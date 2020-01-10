package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//mybatis扫描路径
@MapperScan(basePackages = {"com.liucg.dao"})
public class Liucg24Application {

	public static void main(String[] args) {
		SpringApplication.run(Liucg24Application.class, args);
	}

}
