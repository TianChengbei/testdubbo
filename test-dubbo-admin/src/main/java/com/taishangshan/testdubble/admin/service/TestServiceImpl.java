package com.taishangshan.testdubble.admin.service;

import com.testdubbo.admin.service.TestService;

/**
 * @author tianchengbei
 * @since 2019-11-07
 */
public class TestServiceImpl implements TestService {


    @Override
    public String sayHello(String name) {
        return "admin-->"+name;
    }

}
