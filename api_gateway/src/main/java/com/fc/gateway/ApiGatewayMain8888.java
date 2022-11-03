package com.fc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Everglow
 * Created at 2022/09/16 9:15
 */
@SpringBootApplication(exclude = RedisAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan({"com.fc"})
public class ApiGatewayMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayMain8888.class, args);
    }
}
