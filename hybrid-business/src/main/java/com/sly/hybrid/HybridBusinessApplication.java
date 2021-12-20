package com.sly.hybrid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类型
 *
 * @author SLY
 * @date 2021/11/22
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.sly.hybrid.**.mapper")
public class HybridBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(HybridBusinessApplication.class, args);
    }

}
