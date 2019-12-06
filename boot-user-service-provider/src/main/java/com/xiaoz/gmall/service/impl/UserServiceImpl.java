package com.xiaoz.gmall.service.impl;

import com.xiaoz.gmall.bean.UserAddress;
import com.xiaoz.gmall.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class UserServiceImpl implements UserService {

	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		System.out.println("UserServiceImpl..3.....");
		UserAddress address1 = new UserAddress(1, "北京市昌平", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安", "1", "王老师", "010-56253825", "N");
		return Arrays.asList(address1,address2);
	}

}
