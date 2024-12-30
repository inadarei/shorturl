package dev.jawn.shorturl;

import lombok.extern.log4j.Log4j2;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Log4j2
@SpringBootApplication
public class ShorturlApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ShorturlApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
			return args -> {
					log.info("JWT Token Lifetime in application.properties " + environment.getProperty("dev.jawn.shorturl.jwt.token-lifetime"));
			};
	}
}
