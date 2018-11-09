package com.SelfServiceBarWeb.config.corsConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Muki on 2018/11/9
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PATCH", "OPTIONS", "DELETE")
                .allowedHeaders("content-type", "x-requested-with", "X-Custom-Header", "Authorization", "Origin")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
