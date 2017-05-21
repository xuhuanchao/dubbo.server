package com.xhc.test.dubbo.server.server;

public class SayHelloToClientImpl implements SayHelloToClient {

    @Override
    public String sayHello(String hello) {
        System.out.println("I seceive message :" + hello);
        return hello + ", hello too";
    }

}
