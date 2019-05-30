package com.vasylieva.elective.repository;

import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.CourseStatus;
import com.vasylieva.elective.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByCategoryAndLang(String category, String lang);

    @Query(value = "SELECT courses.* FROM courses c JOIN user_courses uc " +
            "ON(c.id = sc.course_id) " +
            "WHERE uc.user_id = :user_id AND c.course_status IN (:statuses)",
            nativeQuery = true)
    List<Course> findByUserAndCourseStatuses(@Param("user_id") Long userId,
                                             @Param("statuses") String statuses);

    @Query(value = "SELECT c.*, COUNT(uc.course_id) as studentsRegistered, " +
            "COUNT(f.course_id) as reviewsCount " +
            "AVG(f.rating) as rating " +
            "FROM courses c " +
            "JOIN user_courses uc " +
            "JOIN feedback f " +
            "WHERE c.id = uc.course_id AND c.id=f.course_id " +
            "AND c.course_status = 'PUBLISHED' AND c.lang = :lang" +
            "GROUP BY c.*" +
            "ORDER BY rating",
            nativeQuery = true)
    List<Course> findTopRatedCourses(@Param("lang") String lang);


    Optional<Course> findByIdAndLang(Long id, String lang);

    @Query(value = "SELECT c.*, COUNT(uc.course_id) as studentsRegistered, " +
            "COUNT(f.course_id) as reviewsCount " +
            "AVG(f.rating) as rating " +
            "FROM courses c " +
            "JOIN user_courses uc " +
            "JOIN feedback f " +
            "WHERE c.id = uc.course_id AND c.id=f.course_id " +
            "AND c.course_status = 'PUBLISHED' AND c.lang = :lang" +
            "GROUP BY c.*" +
            "ORDER BY rating",
            nativeQuery = true)
    List<Course> findMostPopularCourses(@Param("lang") String lang);

    @Query(value = "SELECT c.*, COUNT(uc.course_id) as studentsRegistered, " +
            "COUNT(f.course_id) as reviewsCount " +
            "AVG(f.rating) as rating " +
            "FROM courses c " +
            "JOIN user_courses uc " +
            "JOIN feedback f " +
            "WHERE c.id = uc.course_id AND c.id=f.course_id " +
            "AND c.course_status = 'PUBLISHED' AND c.lang = :lang" +
            "GROUP BY c.*" +
            "ORDER BY rating",
            nativeQuery = true)
    List<Course> findTrendingCourses(@Param("lang") String lang);
}
