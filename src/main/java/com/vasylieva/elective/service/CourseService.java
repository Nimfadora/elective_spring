package com.vasylieva.elective.service;

import com.vasylieva.elective.model.*;
import com.vasylieva.elective.repository.CourseRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseService {

    private static final int PAGE_SIZE = 10;

    private final CourseRepository courseRepository;
    private final ElasticsearchService elasticsearchService;

    @Autowired
    public CourseService(CourseRepository courseRepository, ElasticsearchService elasticsearchService) {
        this.courseRepository = courseRepository;
        this.elasticsearchService = elasticsearchService;
    }

    @Transactional
    public Course createCourse(Course course) {
        Course savedCourse = courseRepository.save(course);
        savedCourse.getLanguages()
                .forEach(lang -> elasticsearchService.indexCourse(lang, new CourseDocument(savedCourse)));
        return savedCourse;
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

    public List<CourseDocument> findCoursesWithFilters(String search, String lang, String skill, String level,
                                                       String language, Pair<String, String> duration, String sortBy,
                                                       String sortOrder, int page) {
        SortBuilder sortBuilder = SortBuilders.fieldSort(sortBy).order(SortOrder.fromString(sortOrder));
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return elasticsearchService.findCourses(lang, search, skill, level, language, duration, sortBuilder, pageable);
    }

    public List<CourseDocument> findCourses(String search, String lang, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return elasticsearchService.findCourses(lang, search, pageable);
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
