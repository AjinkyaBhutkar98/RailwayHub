package com.ajinkyabhutkar.irctc.config;

import com.ajinkyabhutkar.irctc.interceptors.MyCustomInterceptors;
import com.ajinkyabhutkar.irctc.interceptors.TimeLoggerInterceptor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
        title = "API Documentation for IRCTC Clone by Ajinkya Bhutkar",
        description = "this project is inspired by irctc",
        summary = "implements features such as spring rest apis,security,swagger,token based authentication,monolithic architecture",
        contact = @Contact(name = "ajinkya bhutkar ",email = "ajinkyabhutkar59@gmail.com",url = "https://github.com/AjinkyaBhutkar98")
)
        ,security = @SecurityRequirement(name = "bearerAuth")
)
//security = @SecurityRequirement(name = "bearerAuth") these both should have same name
@SecurityScheme(name = "bearerAuth",type = SecuritySchemeType.HTTP,scheme = "bearer",bearerFormat = "JWT")
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
                                .title("IRCTC Clone by Ajinkya Bhutkar")
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
