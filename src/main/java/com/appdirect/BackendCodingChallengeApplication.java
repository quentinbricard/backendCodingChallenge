package com.appdirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BackendCodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCodingChallengeApplication.class, args);
	}
}
