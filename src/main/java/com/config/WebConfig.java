package com.config;

import com.lesson1.Test;
import com.lesson2hw1.Controller;
import com.lesson2hw1.Route;
import com.lesson2hw1.Step;
import com.lesson2hw1.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com")
public class WebConfig {

    @Bean()
    Test test() {
        return new Test();
    }

    @Bean()
    Controller controller() {
        return new Controller();
    }

    @Bean(initMethod = "beanInit")
    @Scope("prototype")
    Route route() {
        Route route = new Route();
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step());
        route.setSteps(steps);
        return route;
    }

    @Bean(initMethod = "beanInit")
    @Scope("prototype")
    Service service() {
        Service service = new Service();
        service.setName("Cool");
        return service;
    }

    @Bean(initMethod = "beanInit")
    @Scope("prototype")
    Step step() {
        Step step = new Step();
        step.setServiceFrom(service());
        step.setServiceTo(service());
        return step;
    }
}
