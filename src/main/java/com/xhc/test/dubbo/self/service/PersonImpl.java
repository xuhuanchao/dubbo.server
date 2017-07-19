package com.xhc.test.dubbo.self.service;

public class PersonImpl implements IPerson {

    @Override
    public String say(String word) {
        return "hello " + word;
    }

    @Override
    public String eat(String food) {
        return "eat " + food;
    }

}
