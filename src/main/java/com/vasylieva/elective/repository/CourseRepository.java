package com.vasylieva.elective.repository;

import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.status.CourseStatus;
import com.vasylieva.elective.model.status.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query(value = "SELECT c.id, author_courses.user_id, c.category, c.title, c.description, " +
            "COALESCE(AVG(f.rating), 0) as rating, " +
            "COALESCE(COUNT(f.user_id), 0) as studentsReviewed, " +
            "COALESCE(COUNT(course_user.user_id), 0) as studentsRegistered, " +
            "c.duration, c.level, c.img_url, c.skills, c.languages, c.status " +
            "FROM course c " +
            "LEFT JOIN course_user ON c.id = course_user.course_id AND course_user.relationship='STUDENT' " +
            "LEFT JOIN feedback f ON c.id = f.course_id " +
            "INNER JOIN course_user author_courses " +
            "WHERE c.id = author_courses.course_id AND course_user.relationship='AUTHOR' " +
            "AND c.id = CAST(:course_id AS BIGINT) AND c.lang = CAST(:lang AS VARCHAR) " +
            "GROUP BY c.id, c.category, c.title, c.duration, c.level, c.img_url",
            nativeQuery = true)
    Object findById(@Param("course_id") Long id,
                    @Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, " +
            "COALESCE(AVG(f.rating), 0) as rating, " +
            "COALESCE(COUNT(f.user_id), 0) as studentsReviewed, " +
            "COALESCE(COUNT(course_user.user_id), 0) as studentsRegistered, " +
            "c.duration, c.level, c.img_url, c.status " +
            "FROM course c, course_user, feedback f " +
            "WHERE c.id = course_user.course_id " +
            "AND c.id = f.course_id " +
            "AND course_user.user_id = :user_id " +
            "AND c.status IN (:statuses) " +
            "AND c.lang = :lang " +
            "GROUP BY c.id, c.category, c.title, c.duration, c.level, c.img_url, c.status " +
            "ORDER BY c.title",
            nativeQuery = true)
    List<Object> findByUserAndCourseStatuses(@Param("user_id") Long userId,
                                             @Param("statuses") Set<String> statuses,
                                             @Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, " +
            "COALESCE(AVG(f.rating), 0) as rating, " +
            "COALESCE(COUNT(f.user_id), 0) as studentsReviewed, " +
            "COALESCE(COUNT(course_user.user_id), 0) as studentsRegistered, " +
            "c.duration, c.level, c.img_url " +
            "FROM course c " +
            "LEFT JOIN course_user ON c.id = course_user.course_id AND course_user.relationship='STUDENT' " +
            "LEFT JOIN  feedback f ON c.id = f.course_id " +
            "WHERE c.status = 'PUBLISHED' " +
            "AND c.category = CAST(:category AS VARCHAR) " +
            "AND c.lang = CAST(:lang AS VARCHAR) " +
            "GROUP BY c.id, c.category, c.title, c.duration, c.level, c.img_url " +
            "ORDER BY rating DESC",
            nativeQuery = true)
    List<Object> findByCategory(@Param("category") String category,
                                @Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, " +
            "COALESCE(AVG(f.rating), 0) as rating, " +
            "COALESCE(COUNT(f.user_id), 0) as studentsReviewed, " +
            "COALESCE(COUNT(course_user.user_id), 0) as studentsRegistered, " +
            "c.duration, c.level, c.img_url " +
            "FROM course c " +
            "LEFT JOIN course_user ON c.id = course_user.course_id AND course_user.relationship='STUDENT' " +
            "LEFT JOIN  feedback f ON c.id = f.course_id " +
            "WHERE c.status = 'PUBLISHED' " +
            "AND c.lang = CAST(:lang AS VARCHAR) " +
            "GROUP BY c.id, c.category, c.title, c.duration, c.level, c.img_url " +
            "ORDER BY rating DESC " +
            "LIMIT 3",
            nativeQuery = true)
    List<Object> findTopRatedCourses(@Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, " +
            "COALESCE(AVG(f.rating), 0) as rating, " +
            "COALESCE(COUNT(f.user_id), 0) as studentsReviewed, " +
            "COALESCE(COUNT(course_user.user_id), 0) as studentsRegistered, " +
            "c.duration, c.level, c.img_url " +
            "FROM course c " +
            "LEFT JOIN course_user ON c.id = course_user.course_id AND course_user.relationship='STUDENT' " +
            "LEFT JOIN  feedback f ON c.id = f.course_id " +
            "WHERE c.status = 'PUBLISHED' " +
            "AND c.lang = CAST(:lang AS VARCHAR) " +
            "GROUP BY c.id, c.category, c.title, c.duration, c.level, c.img_url " +
            "ORDER BY studentsRegistered DESC " +
            "LIMIT 3",
            nativeQuery = true)
    List<Object> findMostPopularCourses(@Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, " +
            "COALESCE(AVG(f.rating), 0) as rating, " +
            "COALESCE(COUNT(f.user_id), 0) as studentsReviewed, " +
            "COALESCE(COUNT(course_user.user_id), 0) as studentsRegistered, " +
            "c.duration, c.level, c.img_url " +
            "FROM course c " +
            "LEFT JOIN course_user ON c.id = course_user.course_id AND course_user.relationship='STUDENT' " +
            "LEFT JOIN  feedback f ON c.id = f.course_id " +
            "WHERE c.status = 'PUBLISHED' " +
            "AND c.lang = CAST(:lang AS VARCHAR) " +
//            "AND c.date_created > CURRENT_DATE - INTEGER '7'" +
            "GROUP BY c.id, c.category, c.title, c.duration, c.level, c.img_url " +
            "ORDER BY rating DESC " +
            "LIMIT 3",
            nativeQuery = true)
    List<Object> findTrendingCourses(@Param("lang") String lang);
}
