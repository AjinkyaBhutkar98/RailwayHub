package com.ajinkyabhutkar.irctc.config;

import com.ajinkyabhutkar.irctc.interceptors.MyCustomInterceptors;
import com.ajinkyabhutkar.irctc.interceptors.TimeLoggerInterceptor;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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

        @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("IRCTC Clone API by Ajinkya Bhutkar")
                                .version("1.0.0")
                                .description("API documentation for IRCTC project")
                                .termsOfService("https://www.irctc.co.in/terms-of-service")
                                .contact(new io.swagger.v3.oas.models.info.Contact()
                                        .name("IRCTC Support")
                                        .url("https://www.irctc.co.in/contact-us")
                                        .email("abc@gmail.com")
                                ))
                ;

    }
}
