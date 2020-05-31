package io.wangxin.elasticjob.example.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author Wang Xin
 */
@SpringBootApplication(scanBasePackages = "io.wangxin.elasticjob.example.jobs")
//@ImportResource("classpath:META-INF/applicationContext.xml")
public class ExampleJobsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleJobsApplication.class, args);
        System.out.println("jobs 启动成功！");
    }
}
