package com.xiaoz.gmall.service.impl;

import com.xiaoz.gmall.bean.UserAddress;
import com.xiaoz.gmall.service.OrderService;

import java.util.Arrays;
import java.util.List;

/**
 * @author 肖振
 * @create 2019-12-06 15:50
 * @since 1.0
 **/
public class OrderServiceImpl implements OrderService {
    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("UserServiceImpl..3.....");
        UserAddress address1 = new UserAddress(1, "北京市昌平", "1", "李老师", "010-56253825", "Y");
        UserAddress address2 = new UserAddress(2, "深圳市宝安", "1", "王老师", "010-56253825", "N");
        return Arrays.asList(address1,address2);
    }
}
