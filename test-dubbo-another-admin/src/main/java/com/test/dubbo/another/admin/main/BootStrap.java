package com.test.dubbo.another.admin.main;

import com.testdubbo.admin.service.TestService;
import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianchengbei
 * @since 2019-11-07
 */
public class BootStrap {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-import.xml");
        context.start();
        System.err.println("consumer start ------");
        TestService ser = context.getBean(TestService.class);
        String resp = ser.sayHello("刘鹏");
        System.out.println(resp);
//        System.in.read();
    }


}
