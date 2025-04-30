package com.leozinn.redis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void logRedisConfig() {
    System.out.println("Redis Host: " + System.getenv("REDIS_HOST"));
    System.out.println("Redis Port: " + System.getenv("REDIS_PORT"));
    System.out.println("Redis Password: " + System.getenv("REDIS_PWD"));
}

}
