package com.gba.dating4e;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.*;

@SpringBootApplication
public class Dating4eApplication {

	private static final Logger log = LoggerFactory.getLogger(Dating4eApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Dating4eApplication.class, args);
	}

}
