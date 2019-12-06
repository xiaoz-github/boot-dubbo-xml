package com.xiaoz.gmall;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.lang.reflect.Method;

/**
 * 1、导入依赖；
 * 		1）、导入dubbo-starter
 * 		2）、导入dubbo的其他依赖
 *
 * SpringBoot与dubbo整合的三种方式：
 * 1）、导入dubbo-starter，在application.properties配置属性，使用@Service【暴露服务】使用@Reference【引用服务】
 * 2）、保留dubbo xml配置文件;
 * 		导入dubbo-starter，使用@ImportResource导入dubbo的配置文件即可
 * 3）、使用注解API的方式：
 * 		将每一个组件手动创建到容器中,让dubbo来扫描其他的组件
 */
//@EnableDubbo //开启基于注解的dubbo功能
@ImportResource(locations="classpath:provider.xml")
@EnableDubbo
@SpringBootApplication
public class BootUserServiceProviderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(BootUserServiceProviderApplication.class, args);
		// 获取dubbo的provider
		getDubboService(run);

	}

	private static void getDubboService(ConfigurableApplicationContext run) {
		String[] beanDefinitionNames = run.getBeanDefinitionNames();
		for (int i=0; i< beanDefinitionNames.length; i++) {

			try {
				Object bean = run.getBean(beanDefinitionNames[i]);

				if (bean instanceof RegistryConfig) {
					RegistryConfig registryConfig = (RegistryConfig) bean;
					System.out.println("registry --> protocol:" + registryConfig.getProtocol() + "address" + registryConfig.getAddress());
				}

				if (bean instanceof ProtocolConfig) {
					ProtocolConfig protocolConfig = (ProtocolConfig) bean;
					System.out.println("protocol --> name:" + protocolConfig.getName() + "port" + protocolConfig.getPort());
				}

				if (bean instanceof ServiceBean) {
					ServiceBean serviceBean = (ServiceBean) bean;
					Class<?> classUser = Class.forName(serviceBean.getInterface());
					Method[] declaredMethods = classUser.getDeclaredMethods();
					for (Method method : declaredMethods) {
						System.out.println(method);
					}

					System.out.println("serviceBean --> Interface:" + serviceBean.getInterface());
					for (RegistryConfig registryConfig:serviceBean.getRegistries()) {
						System.out.println("registry --> protocol:" + registryConfig.getProtocol() + "   address:" + registryConfig.getAddress());
					}
					for (ProtocolConfig protocolConfig:serviceBean.getProtocols()) {
						System.out.println("protocol --> name:" + protocolConfig.getName() + "   port:" + protocolConfig.getPort());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}