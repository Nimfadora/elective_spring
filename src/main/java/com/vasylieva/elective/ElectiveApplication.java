package com.vasylieva.elective;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.CourseDocument;
import com.vasylieva.elective.model.User;
import com.vasylieva.elective.model.CourseUser;
import com.vasylieva.elective.model.status.Relationship;
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

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class ElectiveApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(SpringBootServletInitializer.class);
    private static final AtomicLong courseIdGenerator = new AtomicLong();

    public static void main(String[] args) {
        SpringApplication.run(ElectiveApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ElectiveApplication.class, ElectiveSecurityConfig.class);
    }

    @Bean
    @Transactional
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
//            Course courseTest = new Course();
//            courseTest.setCourseFeedbacks(Collections.emptySet());
//            courseTest.setLang(Language.EN);
//            courseTest.setCategory("en");
//            courseTest.setTitle("Title");
//            courseTest.setDescription("Title");
//            courseTest.setDurationHours(12);
//            courseTest.setLevel(CourseLevel.BEGINNER);
//            courseTest.setImgUrl("Title");
//            courseTest.setCourseStatus(CourseStatus.APPROVED.toString());
//            courseTest.setSkills(ImmutableSet.of("some", "skills", "here"));
//            courseTest.setLanguages(ImmutableSet.of("en", "ua", "ru"));


            Files.lines(Paths.get(getClass().getResource("/data/dummy_courses.txt").getPath()), StandardCharsets.UTF_8).forEach(str -> {
                try {
                    Course course = mapper.readValue(str, Course.class);
//                    course.setId(courseIdGenerator.getAndIncrement());
                    User author = users.hasNext() ? users.next() : userRepository.findAll().iterator().next();
                    course.setCourseUsers(Collections.singleton(new CourseUser(author, course, Relationship.AUTHOR)));
                    Course courseSaved = courseRepository.save(course);
//                    courseSaved = courseRepository.save(courseSaved);
                    service.indexCourse("en", new CourseDocument(courseSaved, author));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
//            for (Course course : courseRepository.findAll()) {
//                log.info(course.toString());
//                log.info(new ObjectMapper().writeValueAsString(course));
//            }
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
            courseRepository.findByCategory("Programming", "EN").forEach(bauer -> log.info(bauer.toString()));
        };
    }
}
