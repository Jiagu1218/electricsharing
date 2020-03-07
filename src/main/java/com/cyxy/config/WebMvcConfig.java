package com.cyxy.config;

import com.cyxy.interceptor.SessionInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/*//添加拦截器
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**.do");
	}*/
	
}
