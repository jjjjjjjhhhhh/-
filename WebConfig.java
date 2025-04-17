package com.zhhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zql
 * <p>
 * 2024/4/30
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    /**
     * 静态资源加载设置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("file:D:\\xiangmu\\hczl\\hczl-spring\\src\\main\\resources\\static\\");
    }


}
