package org.zsq.job.core;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
/**
 * 采用Spring-boot
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobController.java, V1.0.0 2015年11月19日 下午9:00:50 $
 */
@Controller
@EnableAutoConfiguration
public class JobController {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JobController.class, args);
    }
}
