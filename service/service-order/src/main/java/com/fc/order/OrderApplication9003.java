package com.fc.order;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Everglow
 * Created at 2022/11/10 15:01
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan({"com.fc"})
@MapperScan("com.fc.order.mapper")
public class OrderApplication9003 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication9003.class, args);
    }
}
