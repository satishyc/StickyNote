package com.stickyNote;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.stickyNote")
@EnableMongoRepositories
@OpenAPIDefinition
public class StickyNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(StickyNoteApplication.class, args);
	}


}
