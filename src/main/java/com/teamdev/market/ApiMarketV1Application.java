package com.teamdev.market;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiMarketV1Application implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private static final Logger log = LoggerFactory.getLogger(ApiMarketV1Application.class);


	public static void main(String[] args) {
		SpringApplication.run(ApiMarketV1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		for(int i=0; i<4; i++) {
			String passwordEncrypt = passwordEncoder.encode(password);
			log.info(passwordEncrypt);
		}
		
	}

}
