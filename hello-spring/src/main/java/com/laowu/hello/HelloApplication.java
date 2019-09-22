package com.laowu.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
	@RequestMapping("/hello1")
	public String hello1(){
		return "hello1";
	}
	@RequestMapping("/hello2")
	public String hello2(){
		return "hello2";
	}}
