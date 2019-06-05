package com.vasylieva.elective.repository;

import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.status.CourseStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query(value = "SELECT c.id, author_courses.user_id, c.category, c.title, c.description, " +
            "AVG(f.rating) as rating, COUNT(f.user_id) as studentsReviewed, " +
            "COUNT(user_courses.user_id) as studentsRegistered, c.duration, " +
            "c.level, c.imgUrl, c.skills, c.languages, c.status " +
            "FROM courses c " +
            "JOIN user_courses " +
            "JOIN user_courses author_courses " +
            "JOIN feedback f " +
            "WHERE c.id = user_courses.course_id AND user_courses.relationship='STUDENT' " +
            "AND c.id = author_courses.course_id AND author_courses.relationship='AUTHOR' " +
            "AND c.id = f.course_id " +
            "AND c.id = :course_id AND c.lang = :lang",
            nativeQuery = true)
    Object findById(@Param("course_id") Long id,
                    @Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, AVG(f.rating) as rating, " +
            "COUNT(f.user_id) as studentsReviewed, COUNT(user_courses.user_id) as studentsRegistered, " +
            "c.duration, c.level, c.imgUrl, c.status " +
            "FROM courses c " +
            "JOIN user_courses " +
            "JOIN feedback f " +
            "WHERE c.id = user_courses.course_id " +
            "AND c.id = f.course_id " +
            "AND user_courses.user_id = :user_id " +
            "AND c.status IN (:statuses) " +
            "AND c.lang = :lang " +
            "ORDER BY c.title",
            nativeQuery = true)
    List<Object> findByUserAndCourseStatuses(@Param("user_id") Long userId,
                                             @Param("statuses") List<CourseStatus> statuses,
                                             @Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, AVG(f.rating) as rating, " +
            "COUNT(f.user_id) as studentsReviewed, COUNT(user_courses.user_id) as studentsRegistered, " +
            "c.duration, c.level, c.imgUrl " +
            "FROM courses c " +
            "JOIN user_courses " +
            "JOIN feedback f " +
            "WHERE c.id = user_courses.course_id AND user_courses.relationship='STUDENT' " +
            "AND c.id = f.course_id " +
            "AND c.status = 'PUBLISHED'" +
            "AND c.category = :category " +
            "AND c.lang = :lang " +
            "ORDER BY rating DESC",
            nativeQuery = true)
    List<Object> findByCategory(@Param("category") String category,
                                @Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, AVG(f.rating) as rating, " +
            "COUNT(f.user_id) as studentsReviewed, COUNT(user_courses.user_id) as studentsRegistered, " +
            "c.duration, c.level, c.imgUrl " +
            "FROM courses c " +
            "JOIN user_courses " +
            "JOIN feedback f " +
            "WHERE c.id = user_courses.course_id AND user_courses.relationship='STUDENT' " +
            "AND c.id = f.course_id " +
            "AND c.status = 'PUBLISHED'" +
            "AND lang = :lang " +
            "ORDER BY rating DESC " +
            "LIMIT 3",
            nativeQuery = true)
    List<Object> findTopRatedCourses(@Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, AVG(f.rating) as rating, " +
            "COUNT(f.user_id) as studentsReviewed, COUNT(user_courses.user_id) as studentsRegistered, " +
            "c.duration, c.level, c.imgUrl " +
            "FROM courses c " +
            "JOIN user_courses " +
            "JOIN feedback f " +
            "WHERE c.id = user_courses.course_id AND user_courses.relationship='STUDENT' " +
            "AND c.id = f.course_id " +
            "AND c.status = 'PUBLISHED'" +
            "AND lang = :lang " +
            "ORDER BY studentsRegistered DESC " +
            "LIMIT 3",
            nativeQuery = true)
    List<Object> findMostPopularCourses(@Param("lang") String lang);

    @Query(value = "SELECT c.id, c.category, c.title, AVG(f.rating) as rating, " +
            "COUNT(f.user_id) as studentsReviewed, COUNT(user_courses.user_id) as studentsRegistered, " +
            "c.duration, c.level, c.imgUrl " +
            "FROM courses c " +
            "JOIN user_courses " +
            "JOIN feedback f " +
            "WHERE c.id = user_courses.course_id AND user_courses.relationship='STUDENT' " +
            "AND c.id = f.course_id " +
            "AND c.status = 'PUBLISHED'" +
            "AND lang = :lang " +
            "WHERE c.date_created > CURRENT_DATE - INTEGER '7'" +
            "ORDER BY rating DESC " +
            "LIMIT 3",
            nativeQuery = true)
    List<Object> findTrendingCourses(@Param("lang") String lang);
}
