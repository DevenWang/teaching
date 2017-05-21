package com.whut.teaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.whut.teaching.dao")
public class TeachingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TeachingApplication.class, args);
	}
}
