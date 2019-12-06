package com.xiaoz.gmall.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 肖振
 * @create 2019-12-05 16:52
 * @since 1.0
 **/
public class MainController {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:provider.xml");
    }

}
