package com.example.Student;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // we can use bean to resolve the InternalResourceViewResolver

//    @Bean
//    public ViewResolver getInternalResourceView () {
//        InternalResourceViewResolver ivr = new InternalResourceViewResolver();
//        ivr.setViewClass(JstlView.class);
//        ivr.setPrefix("/templates/");
//        ivr.setSuffix(".jsp");
//        return ivr;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
