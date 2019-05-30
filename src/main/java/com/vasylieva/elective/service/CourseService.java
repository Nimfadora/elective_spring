package com.vasylieva.elective.service;

import com.vasylieva.elective.model.*;
import com.vasylieva.elective.repository.CourseRepository;
import com.vasylieva.elective.repository.CourseStagingRepository;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.vasylieva.elective.model.CourseStatus.STUDENT_COURSE_STATUSES;

@SuppressWarnings("ALL")
@Service
public class CourseService {

    private static final int PAGE_SIZE = 10;

    private final CourseRepository courseRepository;
    private final CourseStagingRepository courseStagingRepository;
    private final ElasticsearchService elasticsearchService;
    private final ValidationService validationService;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         CourseStagingRepository courseStagingRepository,
                         ElasticsearchService elasticsearchService,
                         ValidationService validationService) {
        this.courseRepository = courseRepository;
        this.courseStagingRepository = courseStagingRepository;
        this.elasticsearchService = elasticsearchService;
        this.validationService = validationService;
    }

    public CourseStaging createCourse(CourseStaging course) {
        validationService.validateCourse(course);
        return courseStagingRepository.save(course);
    }

    @Transactional
    public CourseStaging updateCourse(CourseStaging course) {
        CourseStatus courseStatus = CourseStatus.valueOf(course.getCourseStatus());

        switch (courseStatus) {
            case IN_DEVELOPMENT:
                return createCourse(course);
            case PUBLISHED:
                course.setCourseStatus(CourseStatus.IN_DEVELOPMENT.toString());
                return createCourse(course);
            case APPROVED:
                Course saved = courseRepository.save(new Course(course));
                saved.getLanguages()
                        .forEach(lang -> elasticsearchService.indexCourse(lang, new CourseDocument(saved)));
                courseStagingRepository.delete(course);
            default:
                throw new IllegalArgumentException("Invalid course status");
        }
    }

    public void deleteCourse(Course course) {
        CourseStatus courseStatus = CourseStatus.valueOf(course.getCourseStatus());

        switch (courseStatus) {
            case PUBLISHED:
            case IN_DEVELOPMENT:
                courseStagingRepository.deleteById(course.getId());
                Optional<Course> published = courseRepository.findById(course.getId());
                published.ifPresent(c -> {
                    c.setCourseStatus(CourseStatus.IN_MODERATION.toString());
                    createCourse(new CourseStaging(course));
                });
            case APPROVED:
                courseStagingRepository.deleteById(course.getId());
            default:
                throw new IllegalArgumentException("Cannot delete: invalid course status");
        }
    }

    public List<Course> getTopRatedCourses(String lang) {
        return courseRepository.findTopRatedCourses(lang);
    }

    public List<Course> getMostPopularCourses(String lang) {
        return courseRepository.findMostPopularCourses(lang);

    }

    public List<Course> getTrendingCourses(String lang) {
        return courseRepository.findTrendingCourses(lang);
    }

    public Optional<Course> getCourse(long id, String lang) {
        return courseRepository.findByIdAndLang(id, lang);
    }

    public List<Course> getCoursesByCategory(String category, String lang) {
        return courseRepository.findByCategoryAndLang(category, lang);
    }

    public List<CourseDocument> findCoursesWithFilters(String search, String lang, String skill, String level,
                                                       String language, Pair<Integer, Integer> duration, String sortBy,
                                                       String sortOrder, int page) {
        SortBuilder sortBuilder = SortBuilders.fieldSort(sortBy).order(SortOrder.fromString(sortOrder));
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return elasticsearchService.findCourses(lang, search, skill, level, language, duration, sortBuilder, pageable);
    }

    public List<CourseDocument> findCourses(String search, String lang, String sortBy,
                                            String sortOrder, int page) {
        SortBuilder sortBuilder = SortBuilders.fieldSort(sortBy).order(SortOrder.fromString(sortOrder));
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return elasticsearchService.findCourses(lang, search, sortBuilder, pageable);
    }

    public List<Course> getCoursesByUserAndStatus(User user, List<CourseStatus> status, String lang) {
        if (user.getRole() == Role.STUDENT) {
            return getStudentCourses(user.getId(), status, lang);
        }
        return getAuthorCourses(user.getId(), status, lang);
    }

    private List<Course> getStudentCourses(Long studentId, List<CourseStatus> statuses, String lang) {
        String statusString = statuses.stream().map(CourseStatus::toString).collect(Collectors.joining(","));
        if (statuses.size() != 1 || !statuses.get(0).isStudentCourseStatus()) {
            throw new IllegalArgumentException("Invalid course status: " + statusString);
        }
        return courseRepository.findByUserAndCourseStatuses(studentId, statusString);
    }

    private List<Course> getAuthorCourses(Long authorId, List<CourseStatus> statuses, String lang) {
        String statusString = statuses.stream().map(CourseStatus::toString).collect(Collectors.joining(","));
        if (statuses.stream().anyMatch(STUDENT_COURSE_STATUSES::contains)) {
            throw new IllegalArgumentException("Such status does not exist:" + statusString);
        }
        return courseRepository.findByUserAndCourseStatuses(authorId, statusString);
    }
}
