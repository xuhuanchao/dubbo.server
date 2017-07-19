package com.xhc.test.dubbo.self.provider;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Policy.Parameters;
import java.util.Properties;

import com.xhc.test.dubbo.self.service.PersonImpl;
import com.xhc.test.dubbo.self.service.StudyImpl;

public class Provider {

    private static Properties p = new Properties();
    
    private static Class getService(String clazz) {
        String impl = p.getProperty(clazz);
        try {
            return Class.forName(impl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("启动服务，监听请求");
        p.load(Provider.class.getResourceAsStream("./service.properties"));
        ServerSocket server = new ServerSocket(8888);
        
        while(true){
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            //接收数据顺序 1.serviceName服务名 2.方法名 3.方法参数类型 4.方法参数值
            String serviceName = ois.readUTF();
            String methodName = ois.readUTF();
            Class[] paramTyps = (Class[])ois.readObject();
            Object[] params = (Object[])ois.readObject();
            
            Class clazz = getService(serviceName);
            Method method = clazz.getMethod(methodName, paramTyps);
            Object obj = method.invoke(clazz.newInstance(), params);
            
            
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(obj);
            
            oos.flush();
            oos.close();
            ois.close();
        }
    }
}
