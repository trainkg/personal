package org.zsq.job.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 应用入口
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: Application.java, V1.0.0 2015年11月20日 下午8:45:49 $
 */
// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
@ImportResource(value={"spring-context.xml"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
