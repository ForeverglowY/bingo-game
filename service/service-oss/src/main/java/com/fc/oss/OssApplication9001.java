package com.fc.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Everglow
 * Created at 2022/11/03 15:04
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.fc")
@EnableDiscoveryClient
public class OssApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication9001.class, args);
    }
}
