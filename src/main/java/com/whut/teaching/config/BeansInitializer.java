package com.whut.teaching.config;

import com.whut.teaching.interceptor.LoginInterceptor;
import com.whut.teaching.interceptor.RequestCounterInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wpc on 2017/5/21.
 */
@Configuration
public class BeansInitializer {

    @Bean(name = "LoginInterceptor")
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean(name = "RequestCounterInterceptor")
    public RequestCounterInterceptor requestCounterInterceptor() {
        return new RequestCounterInterceptor();
    }

}
