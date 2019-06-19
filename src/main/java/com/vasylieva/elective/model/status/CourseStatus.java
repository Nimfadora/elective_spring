package com.vasylieva.elective.model.status;

import com.google.common.collect.ImmutableSet;
import com.vasylieva.elective.model.Course;

import java.util.Set;

public enum CourseStatus {
    BOOKMARKED, ARCHIVED, ACTIVE, PUBLISHED, WIP, IN_DEVELOPMENT, IN_MODERATION, APPROVED;

    public static final Set<CourseStatus> STUDENT_COURSE_RELATIONSHIPS = ImmutableSet.of(
            ACTIVE, ARCHIVED, BOOKMARKED
    );

    public static final Set<CourseStatus> AUTHOR_COURSE_RELATIONSHIPS = ImmutableSet.of(
            PUBLISHED, WIP, IN_DEVELOPMENT, IN_MODERATION, APPROVED
    );

    public static final Set<String> WIP_STATUSES = ImmutableSet.of(IN_DEVELOPMENT.toString(), IN_MODERATION.toString());

    private boolean isStatusAllowed(Role role) {
        if (role == Role.STUDENT) {
            return STUDENT_COURSE_RELATIONSHIPS.contains(this);
        } else if (role == Role.AUTHOR) {
            return AUTHOR_COURSE_RELATIONSHIPS.contains(this);
        }
        return false;
    }

    public static CourseStatus parseCourseStatus(String status, Role role) {
        if(status == null) {
            return role == Role.AUTHOR ? PUBLISHED : ACTIVE;
        }
        CourseStatus courseStatus = CourseStatus.valueOf(status);
        if (!courseStatus.isStatusAllowed(role)) {
            throw new IllegalArgumentException("Status does not exist: " + courseStatus.toString());
        }
        return courseStatus;
    }
}
