package com.vasylieva.elective.util.mapper;

import com.vasylieva.elective.model.CourseLevel;
import com.vasylieva.elective.model.CourseSearchDTO;
import com.vasylieva.elective.model.CourseStatus;

public class CourseMapper {
    private static final int FIELDS_COUNT = 9;
    private static final int FIELDS_WITH_STATUS_COUNT = 10;

    public static CourseSearchDTO mapCourseSearchDto(Object courseObj) {
        Object[] fields = (Object[]) courseObj;
        assert fields.length == FIELDS_COUNT || fields.length == FIELDS_WITH_STATUS_COUNT;

        CourseSearchDTO dto = new CourseSearchDTO();
        dto.setId(Long.parseLong(fields[0].toString()));
        dto.setCategory(fields[1].toString());
        dto.setTitle(fields[2].toString());
        dto.setRating(Double.parseDouble(fields[3].toString()));
        dto.setStudentsReviewed(Long.parseLong(fields[4].toString()));
        dto.setStudentsRegistered(Long.parseLong(fields[5].toString()));
        dto.setDurationHours(Integer.parseInt(fields[6].toString()));
        dto.setLevel(CourseLevel.valueOf(fields[7].toString()));
        dto.setImgUrl(fields[8].toString());

        if(fields.length == FIELDS_WITH_STATUS_COUNT) {
            dto.setCourseStatus(CourseStatus.valueOf(fields[9].toString()));
        }
        return dto;
    }
}
