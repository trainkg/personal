package org.zsq.job.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 采用Spring-boot
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobController.java, V1.0.0 2015年11月19日 下午9:00:50 $
 */
@Controller
public class JobController {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
