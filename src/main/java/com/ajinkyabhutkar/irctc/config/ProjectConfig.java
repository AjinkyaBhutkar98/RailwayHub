package com.ajinkyabhutkar.irctc.config;

import com.ajinkyabhutkar.irctc.interceptors.MyCustomInterceptors;
import com.ajinkyabhutkar.irctc.interceptors.TimeLoggerInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {


    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

    @Autowired
    private MyCustomInterceptors customInterceptors;

    @Autowired
    private TimeLoggerInterceptor timeLoggerInterceptor;


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
////        registry.addInterceptor(customInterceptors).addPathPatterns("/trains");
//        registry.addInterceptor(customInterceptors).addPathPatterns("/trains","/stations").excludePathPatterns("/trains/list");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        registry.addInterceptor(customInterceptors).addPathPatterns("/trains");
        registry.addInterceptor(timeLoggerInterceptor).addPathPatterns("/trains","/stations").excludePathPatterns("/trains/list");
    }
}
