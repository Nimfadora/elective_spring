package com.vasylieva.elective.repository;

import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.CourseStatus;
import com.vasylieva.elective.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByCategory(String category);

    List<Course> findByAuthorAndCourseStatus(User author, CourseStatus courseStatus);

    @Query(value = "SELECT COURSES.* FROM COURSES c JOIN student_courses sc ON(c.id = sc.course_id) " +
            "WHERE sc.user_id = :student_id AND c.course_status = :status",
            nativeQuery = true)
    List<Course> findByStudentAndCourseStatus(@Param("student_id") Long student_id,
                                              @Param("status") CourseStatus status);

}
