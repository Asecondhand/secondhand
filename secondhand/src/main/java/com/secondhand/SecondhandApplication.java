package com.secondhand;

import com.secondhand.common.ws.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author zangjan
 */
//@Import(SpringUtil.class)
@SpringBootApplication
@MapperScan(basePackages = "com.secondhand.module.*.mapper")
public class SecondhandApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondhandApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init (){
//        //启动时  将mysql中数据 添加到 es中
//
//    }
    @Bean SpringUtil springUtil(){
        return new SpringUtil();
    }
}
