package com.newvision.springstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
public class SpringstartApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringstartApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringstartApplication.class, args);
	}

}
