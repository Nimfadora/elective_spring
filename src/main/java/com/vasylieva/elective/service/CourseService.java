package com.vasylieva.elective.service;

import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.CourseStatus;
import com.vasylieva.elective.model.Role;
import com.vasylieva.elective.model.User;
import com.vasylieva.elective.repository.CourseRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        course.setCourseStatus(CourseStatus.IN_DEVELOPMENT.toString());
        return courseRepository.save(course);
    }

    public Course getCourse(long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public List<Course> getCourses(String skill, String level, String language, Pair<String, String> duration,
                                   String sortBy, String sortOrder) {
        return new ArrayList<>();
    }

    public List<Course> getCoursesByUserAndStatus(User user, CourseStatus status) {
        if (user.getRole() == Role.STUDENT) {
            return getStudentCourses(user.getId(), status);
        }
        return getAuthorCourses(user, status);
    }

    private List<Course> getStudentCourses(Long studentId, CourseStatus status) {
        if (!status.isStudentCourseStatus()) {
            throw new IllegalArgumentException("Such status does not exist: " + status);
        }
        return courseRepository.findByStudentAndCourseStatus(studentId, status);
    }

    private List<Course> getAuthorCourses(User author, CourseStatus status) {
        if (status.isStudentCourseStatus()) {
            throw new IllegalArgumentException("Such status does not exist:" + status);
        }
        return courseRepository.findByAuthorAndCourseStatus(author, status);
    }
}
