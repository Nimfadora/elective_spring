package com.vasylieva.elective.model.status;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum Relationship {
    BOOKMARKED, ARCHIVED, ACTIVE, AUTHOR;

    public static final List<Relationship> STUDENT_COURSE_STATUSES = ImmutableList.of(
            ACTIVE, ARCHIVED, BOOKMARKED
    );

    public boolean isStudentCourseStatus() {
        return STUDENT_COURSE_STATUSES.contains(this);
    }
}
