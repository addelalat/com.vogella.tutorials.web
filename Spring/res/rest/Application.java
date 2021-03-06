package com.vogella.spring.data.rest;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner jpaSample(TodoRepository todoRepo) {
		return (args) -> {
			
			// save 2 todos in the H2 database
			todoRepo.save(new Todo("Test"));

			Todo todo = new Todo("Detailed test");
			Date date = new Date();
			todo.setDueDate(date);
			todo.setDescription("Detailed description");
			todoRepo.save(todo);

			RestTemplate restTemplate = new RestTemplate();
			Todo firstTodo = restTemplate.getForObject("http://localhost:8080/todoes/1", Todo.class);
			Todo secondTodo = restTemplate.getForObject("http://localhost:8080/todoes/2", Todo.class);

			System.out.println(firstTodo);
			System.out.println(secondTodo);
		};
	}
}
