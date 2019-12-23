package com.codegym.airbnb;

import com.codegym.airbnb.service.CommentService;
import com.codegym.airbnb.service.HostService;
import com.codegym.airbnb.service.OrderHouseService;
import com.codegym.airbnb.service.StatusHouseService;
import com.codegym.airbnb.service.impl.CommentServiceImpl;
import com.codegym.airbnb.service.impl.HostServiceImpl;
import com.codegym.airbnb.service.impl.OrderHouseServiceImpl;
import com.codegym.airbnb.service.impl.StatusHouseServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AirbnbApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AirbnbApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AirbnbApplication.class);
    }

    @Bean
    public StatusHouseService statusHouseService(){
        return  new StatusHouseServiceImpl();
    }

    @Bean
    public HostService hostService(){
        return new HostServiceImpl();
    }

    @Bean
    public OrderHouseService orderHouseService() {
        return new OrderHouseServiceImpl();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl();
    }
}
