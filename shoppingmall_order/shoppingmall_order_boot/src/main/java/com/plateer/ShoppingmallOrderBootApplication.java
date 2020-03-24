package com.plateer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
@EnableSwagger2
public class ShoppingmallOrderBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingmallOrderBootApplication.class, args);
		log.debug("hello");
	}
}
