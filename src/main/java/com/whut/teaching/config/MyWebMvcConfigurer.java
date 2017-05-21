package com.whut.teaching.config;

import com.whut.teaching.interceptor.LoginInterceptor;
import com.whut.teaching.interceptor.RequestCounterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wpc on 2017/5/21.
 */
@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private RequestCounterInterceptor requestCounterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor).addPathPatterns("/student/*", "/teacher/*");
        registry.addInterceptor(requestCounterInterceptor).addPathPatterns("/*");


        super.addInterceptors(registry);
    }
}
