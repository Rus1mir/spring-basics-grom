package com.lesson2hw1;

import java.util.List;
import java.util.Random;

public class Route {

    private String id;
    private List steps;

    public Route(String id, List steps) {
        this.id = id;
        this.steps = steps;
    }

    public Route() {
    }

    public void beanInit() {
        id = String.valueOf(new Random().nextLong());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getSteps() {
        return steps;
    }

    public void setSteps(List steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", steps=" + steps +
                '}';
    }
}
