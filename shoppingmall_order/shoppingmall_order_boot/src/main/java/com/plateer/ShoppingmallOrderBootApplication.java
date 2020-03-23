package com.plateer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ShoppingmallOrderBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingmallOrderBootApplication.class, args);
		log.debug("hello");
	}
}
