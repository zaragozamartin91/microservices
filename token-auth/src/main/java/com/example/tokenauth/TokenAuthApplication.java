package com.example.tokenauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TokenAuthApplication {
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/users/*");

		return registrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(TokenAuthApplication.class, args);
	}
}
