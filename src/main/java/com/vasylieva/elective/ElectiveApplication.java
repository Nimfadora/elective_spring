package com.vasylieva.elective;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;
import com.vasylieva.elective.model.*;
import com.vasylieva.elective.repository.CourseRepository;
import com.vasylieva.elective.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
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
	public CommandLineRunner demo(CourseRepository courseRepository, UserRepository userRepository) {
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
				courseRepository.save(course);
			});

//			courseRepository.save(new Course(author, "Programming", "Advanced Javascript",
//					"In only three hours you will learn enough " +
//					"javascript to transform from a junior javascript developer into a senior javascript guru." +
//					"You will dramatically improve your chances of getting past a technical interview, landing" +
//					" that dream job and earning more money. If you are like me you learnt Javascript by just " +
//					"muddling along, seeing what works and learning a thing or two everyday.\n" +
//					"However without a grasp of the deeper fundamentals you will hit quite a few head scratch-y" +
//					" issues, introduce bugs, find it hard to read and understand framework and library code and" +
//					" won’t be considered a senior developer. This unique course teaches you advanced javascript" +
//					" knowledge through a series of 20 interview questions, with regular quiz's on the way through" +
//					" to cement your knowledge. This course covers ES5 version of javascript, the only officially " +
//					"supported version in all browsers.\n" +
//					"What are you going to learn?\n" +
//					"Types & Equality: The different types in JS and how to check if two values are really equal?\n" +
//					"Scopes: The different scopes a variable can be declared in and how to manipulate those scopes.\n" +
//					"Object Orientation: How to perform OO in Javascript with both the Prototype Pattern and the " +
//					"Pseudo-Classical/Constructor Pattern.\n" +
//					"Advanced topics in Networking such as CORS and JSONP.\n" +
//					"Advanced topics in Event Handling such as the different event phases.\n" +
//					"Why an interview format?\n" +
//					"I find that it's only when i'm facing an upcoming interview that I get into gear and really make" +
//					" sure I have a deep understanding of what I claim to know.\n" +
//					"I might know the best practice for how to solve a problem, but do I know why?\n" +
//					"Javascript interviews are designed to dig deeper into your knowledge of a subject, see if you are" +
//					" just mimicking what you have read or if you have a proper understanding.\n" +
//					"Also it's FUN, what's more satisfying than learning something, then passing a test!",
//					4.6, 3674, 15077, 3.5, CourseLevel.INTERMEDIATE,
//					"https://i.udemycdn.com/course/240x135/1901422_8f67_3.jpg",
//					ImmutableSet.of("JavaScript", "OOP", "Programming", "Interview"),
//					ImmutableSet.of("ua", "ru", "en"), CourseStatus.IN_DEVELOPMENT.toString()));

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
