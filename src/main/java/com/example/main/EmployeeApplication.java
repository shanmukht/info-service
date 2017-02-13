package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@SpringBootApplication
@ComponentScan({ "com.example" })
public class EmployeeApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmployeeApplication.class);
	}

	@ControllerAdvice
	static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
		public JsonpAdvice() {
			super("callback");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}


