package com.vasylieva.elective.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum CourseStatus {
    // student course status
    ACTIVE, ARCHIVED, BOOKMARKED,
    // course status
    PUBLISHED, IN_DEVELOPMENT, IN_MODERATION, APPROVED;

    public static final List<CourseStatus> STUDENT_COURSE_STATUSES = ImmutableList.of(
            ACTIVE, ARCHIVED, BOOKMARKED
    );

    public boolean isStudentCourseStatus() {
        return STUDENT_COURSE_STATUSES.contains(this);
    }
}
