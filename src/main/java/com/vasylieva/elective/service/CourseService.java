package com.vasylieva.elective.service;

import com.vasylieva.elective.model.*;
import com.vasylieva.elective.model.dto.CourseDTO;
import com.vasylieva.elective.model.dto.CourseSearchDTO;
import com.vasylieva.elective.model.status.CourseStatus;
import com.vasylieva.elective.model.status.Relationship;
import com.vasylieva.elective.model.status.Role;
import com.vasylieva.elective.repository.CourseRepository;
import com.vasylieva.elective.repository.CourseStagingRepository;
import com.vasylieva.elective.util.CourseMapper;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static com.vasylieva.elective.model.status.CourseStatus.STUDENT_COURSE_STATUSES;
import static com.vasylieva.elective.model.status.Relationship.STUDENT_COURSE_STATUSES;

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

    public CourseStaging saveCourseToStaging(CourseStaging course) {
        validationService.validateCourse(course);
        return courseStagingRepository.save(course);
    }

    @Transactional
    public CourseStaging updateCourse(CourseStaging course) {
        CourseStatus courseStatus = CourseStatus.valueOf(course.getCourseStatus());

        switch (courseStatus) {
            case IN_DEVELOPMENT:
                return saveCourseToStaging(course);
            case APPROVED:
                Course saved = courseRepository.save(new Course(course));
                saved.getLanguages().forEach(lang -> elasticsearchService.indexCourse(lang, new CourseDocument(saved)));
                courseStagingRepository.deleteById(course.getId());
            default:
                throw new IllegalArgumentException("Invalid course status");
        }
    }

    public void deleteCourse(Course course) {
        CourseStatus courseStatus = CourseStatus.valueOf(course.getCourseStatus());
        switch (courseStatus) {
            case IN_MODERATION:
            case IN_DEVELOPMENT:
                Optional<Course> published = courseRepository.findById(course.getId());
                if (!published.isPresent()) {
                    courseStagingRepository.deleteById(course.getId());
                    break;
                }
            default:
                throw new IllegalArgumentException("Cannot delete: invalid course status");
        }
    }

    public CourseDTO getCourse(long id, String lang) {
        return CourseMapper.mapCourseDTO(courseRepository.findById(id, lang));
    }

    public List<CourseSearchDTO> getTopRatedCourses(String lang) {
        return mapCourses(courseRepository.findTopRatedCourses(lang));
    }

    public List<CourseSearchDTO> getMostPopularCourses(String lang) {
        return mapCourses(courseRepository.findMostPopularCourses(lang));
    }

    public List<CourseSearchDTO> getTrendingCourses(String lang) {
        return mapCourses(courseRepository.findTrendingCourses(lang));
    }

    public List<CourseSearchDTO> getCoursesByCategory(String category, String lang, String sortBy, String sortOrder,
                                                      int page) {
        return mapCourses(courseRepository.findByCategory(category, lang));
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

    public List<CourseSearchDTO> getCoursesByUserAndRelationship(User user, String relationship, String lang) {
        if (user.getRole() == Role.STUDENT) {
            return getStudentCourses(user.getId(), status, lang);
        }
        return getAuthorCourses(user.getId(), status, lang);
    }

    private List<CourseSearchDTO> getStudentCourses(Long studentId, List<CourseStatus> statuses, String lang) {
        if (statuses.size() != 1 || !statuses.get(0).isStudentCourseStatus()) {
            throw new IllegalArgumentException("Invalid course status: " + statuses.stream()
                    .map(CourseStatus::toString).collect(Collectors.joining(",")));
        }
        return mapCourses(courseRepository.findByUserAndCourseStatuses(studentId, statuses, lang));
    }

    private List<CourseSearchDTO> getAuthorCourses(Long authorId, List<CourseStatus> statuses, String lang) {
        if (statuses.stream().anyMatch(STUDENT_COURSE_STATUSES::contains)) {
            throw new IllegalArgumentException("Such status does not exist:" + statuses.stream()
                    .map(CourseStatus::toString).collect(Collectors.joining(",")));
        }
        return mapCourses(courseRepository.findByUserAndCourseStatuses(authorId, statuses, lang));
    }

    private List<CourseSearchDTO> mapCourses(List<Object> courses) {
        return courses.stream().map(CourseMapper::mapCourseSearchDto).collect(Collectors.toList());
    }

    public List<String> getTopSearchResults(String searchString, String lang) {
        return null;
    }
}
