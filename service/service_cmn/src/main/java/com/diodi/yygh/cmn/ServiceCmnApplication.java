package com.diodi.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author diodi
 * @create 2021-07-29-19:36
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.diodi")
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run( ServiceCmnApplication.class,args);
        System.out.println("******************************服务启动成功******************************");
    }
}
