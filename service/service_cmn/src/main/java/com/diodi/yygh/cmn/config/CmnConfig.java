package com.diodi.yygh.cmn.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author diodi
 * @create 2021-07-24-20:27
 */
@Configuration
@MapperScan("com.diodi.yygh.cmn.mapper")
public class CmnConfig {
    /**
     * 分页插件
     * @author  diodi 
     * @param 
     * @returns com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @Date  19:43 2021/7/29
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
