package com.lesson2hw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Route route;

    @Autowired
    private Service service1;

    @Autowired
    private Step step;

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = "text/plain")
    public @ResponseBody
    String callByBean() {


        System.out.println(route.getId());
        System.out.println(route.getSteps());

        System.out.println(service1.getId());
        System.out.println(service1.getName());
        System.out.println(service1.getParamsToCall());

        System.out.println(step.getId());
        System.out.println(step.getParamsServiceFrom());
        System.out.println(step.getParamsServiceTo());
        System.out.println(step.getServiceFrom());
        System.out.println(step.getServiceTo());

        return "OK";
    }
}
