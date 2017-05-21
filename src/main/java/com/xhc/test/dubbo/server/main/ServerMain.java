package com.xhc.test.dubbo.server.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerMain {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"applicationProvider.xml"});
        context.start();
        System.out.println("按任意键退出");
        System.in.read();
        
    }
}
