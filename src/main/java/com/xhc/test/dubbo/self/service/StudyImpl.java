package com.xhc.test.dubbo.self.service;

public class StudyImpl implements IStudy {

    @Override
    public String study(String name) {
        return "begin study " + name;
    }

}
