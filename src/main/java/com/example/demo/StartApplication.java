package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ComponentScan("controller")
public class StartApplication {
	@RequestMapping("/home")
	String home(){
		return "index.html";
	}
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

}
