package com.diodi.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author diodi
 * @create 2021-07-24-19:16
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.diodi")
public class ServiceHospMain8201 {
    public static void main(String[] args) {
        SpringApplication.run( ServiceHospMain8201.class,args);
        System.out.println("******************************服务启动成功******************************");
    }
}
