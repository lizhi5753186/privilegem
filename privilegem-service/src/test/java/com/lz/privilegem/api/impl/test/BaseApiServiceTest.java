package com.lz.privilegem.api.impl.test;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lz.privilegem.api.impl.BaseApiServiceImpl;
import com.lz.privilegem.entity.User;

/**
 * Created by lizhi on 2017/7/7.
 */
public class BaseApiServiceTest {
    private static BaseApiServiceImpl service;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        service = (BaseApiServiceImpl)ctx.getBean("BaseApiServiceImpl");
        ctx.close();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserByUsername() {
        User user = service.getUserByUsername("root");
        if(user == null)
        {
            System.out.println("user is null");
        } else {
            System.out.println("user is not null");
        }
    }
}
