package com.taishangshan.testdubble.admin.main;

import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author tianchengbei
 * @since 2019-11-07
 */
public class BootStrap {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-export.xml");
        context.start();
        System.err.println("服务已经启动------------");
        System.in.read();
    }


}
