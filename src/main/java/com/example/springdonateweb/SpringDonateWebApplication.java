package com.example.springdonateweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;


@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SpringDonateWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDonateWebApplication.class, args);
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
	}

}
