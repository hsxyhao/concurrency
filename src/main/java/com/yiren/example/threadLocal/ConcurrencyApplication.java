package com.yiren.example.threadLocal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wanghao
 * create 2018-04-11 18:14
 **/
@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean httpFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new HttpFilter());
        bean.addUrlPatterns("/threadLocal/*");
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HttpInterceptor())
                .addPathPatterns("/**");
    }
}
