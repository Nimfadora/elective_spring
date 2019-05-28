package com.vasylieva.elective;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.CourseDocument;
import com.vasylieva.elective.model.User;
import com.vasylieva.elective.repository.CourseRepository;
import com.vasylieva.elective.repository.UserRepository;
import com.vasylieva.elective.service.ElasticsearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

@SpringBootApplication
public class ElectiveApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(SpringBootServletInitializer.class);

	public static void main(String[] args) {
		SpringApplication.run(ElectiveApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ElectiveApplication.class);
	}

	@Bean
	public CommandLineRunner demo(CourseRepository courseRepository, UserRepository userRepository, ElasticsearchService service) {
		return (args) -> {
			ObjectMapper mapper = new ObjectMapper();

			Files.lines(Paths.get(getClass().getResource("/data/dummy_authors.txt").getPath()), StandardCharsets.UTF_8).forEach(str -> {
				try {
					userRepository.save(mapper.readValue(str, User.class));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			Iterator<User> users = userRepository.findAll().iterator();
			Files.lines(Paths.get(getClass().getResource("/data/dummy_courses.txt").getPath()), StandardCharsets.UTF_8).forEach(str -> {
				Course course = null;
				try {
					course = mapper.readValue(str, Course.class);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if(users.hasNext()) {
					course.setAuthor(users.next());

				} else {
					course.setAuthor(userRepository.findAll().iterator().next());
				}
				Course courseSaved = courseRepository.save(course);
				service.indexCourse("test", new CourseDocument(courseSaved));
			});

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Course course : courseRepository.findAll()) {
				log.info(course.toString());
				log.info(new ObjectMapper().writeValueAsString(course));
			}
			log.info("");

			// fetch an individual customer by ID
			courseRepository.findById(1L)
					.ifPresent(customer -> {
						log.info("Customer found with findById(1L):");
						log.info("--------------------------------");
						log.info(customer.toString());
						log.info("");
					});

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			courseRepository.findByCategory("Programming").forEach(bauer -> log.info(bauer.toString()));
		};
	}
}
