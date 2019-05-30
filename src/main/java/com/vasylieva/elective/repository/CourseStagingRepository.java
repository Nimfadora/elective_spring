package com.vasylieva.elective.repository;

import com.vasylieva.elective.model.CourseStaging;
import com.vasylieva.elective.model.CourseStatus;
import com.vasylieva.elective.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseStagingRepository extends CrudRepository<CourseStaging, Long> {

    List<CourseStaging> findByCategory(String category);

    List<CourseStaging> findByAuthorAndCourseStatus(User author, CourseStatus courseStatus);

    @Query(value = "SELECT courses.* FROM courses c JOIN user_courses uc ON(c.id = sc.course_id) " +
            "WHERE uc.user_id = :user_id AND c.course_status IN (:statuses)",
            nativeQuery = true)
    List<CourseStaging> findByUserAndCourseStatuses(@Param("user_id") Long userId, @Param("statuses") String statuses);
}
