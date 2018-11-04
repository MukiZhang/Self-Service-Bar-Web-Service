package com.SelfServiceBarWeb.config.FilterRegisterConfig;

import com.SelfServiceBarWeb.filter.RequestAndResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Muki on 2018/11/4
 */
@Configuration
public class FilterRegistration {
    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new RequestAndResponseFilter());
        registration.setOrder(0);
        return registration;
    }
}