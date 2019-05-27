package com.nc.training.center.chat.configuratons;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MainConfiguration implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {

      //  registry.addViewController("/UserPage").setViewName("UserPage");
    }

}
