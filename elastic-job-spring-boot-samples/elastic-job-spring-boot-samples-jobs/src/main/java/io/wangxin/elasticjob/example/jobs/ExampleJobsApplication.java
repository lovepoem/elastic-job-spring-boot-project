package io.wangxin.elasticjob.example.jobs;

import io.wangxin.elasticjob.example.zk.EmbedZookeeperServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Wang Xin
 */
@SpringBootApplication
public class ExampleJobsApplication {
    public static void main(String[] args) {
        EmbedZookeeperServer.start(6181);
        SpringApplication.run(ExampleJobsApplication.class, args);
    }
}
