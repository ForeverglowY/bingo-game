package com.fc.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Everglow
 * Created at 2022/11/01 11:38
 */
@SpringBootApplication
@ComponentScan("com.fc")
@EnableDiscoveryClient
@MapperScan("com.fc.management.mapper")
@EnableFeignClients
public class ManagementMain9000 {
    public static void main(String[] args) {
        SpringApplication.run(ManagementMain9000.class, args);
    }
}