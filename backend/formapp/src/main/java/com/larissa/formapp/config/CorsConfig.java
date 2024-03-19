package com.larissa.formapp.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class CorsConfig {

  @Bean
  public FilterRegistrationBean<CustomCorsFilter> customCorsFilter() {
    FilterRegistrationBean<CustomCorsFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new CustomCorsFilter());
    registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return registrationBean;
  }
}