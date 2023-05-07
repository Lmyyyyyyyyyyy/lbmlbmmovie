package com.lbm.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableCaching
@SpringBootApplication
@MapperScan("com.lbm.movie.mapper")
public class lbmmovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(lbmmovieApplication.class, args);
    }

}
