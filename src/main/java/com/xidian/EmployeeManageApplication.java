package com.xidian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.xidian")
public class EmployeeManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManageApplication.class, args);
	}
}
