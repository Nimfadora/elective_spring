package com.vasylieva.elective.util;

import com.vasylieva.elective.model.dto.CourseDTO;
import com.vasylieva.elective.model.status.CourseLevel;
import com.vasylieva.elective.model.dto.CourseSearchDTO;
import com.vasylieva.elective.model.status.CourseStatus;
import com.vasylieva.elective.util.SetJsonConverter;

@SuppressWarnings("Duplicates")
public class CourseMapper {
    private static final int DTO_FIELDS_COUNT = 14;
    private static final int SEARCH_DTO_FIELDS_COUNT = 9;
    private static final int SEARCH_DTO_FIELDS_WITH_STATUS_COUNT = 10;
    private static final SetJsonConverter SET_CONVERTER = new SetJsonConverter();

    private CourseMapper() {
    }

    public static CourseSearchDTO mapCourseSearchDto(Object courseObj) {
        Object[] fields = (Object[]) courseObj;
        assert fields.length == SEARCH_DTO_FIELDS_COUNT || fields.length == SEARCH_DTO_FIELDS_WITH_STATUS_COUNT;

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

        if(fields.length == SEARCH_DTO_FIELDS_WITH_STATUS_COUNT) {
            dto.setCourseStatus(CourseStatus.valueOf(fields[9].toString()));
        }
        return dto;
    }

    public static CourseDTO mapCourseDTO(Object courseObj) {
        Object[] fields = (Object[]) courseObj;
        assert fields.length == DTO_FIELDS_COUNT;

        CourseDTO dto = new CourseDTO();
        dto.setId(Long.parseLong(fields[0].toString()));
        dto.setAuthorName(fields[1].toString());
        dto.setAuthorCompany(fields[2].toString());
        dto.setTitle(fields[3].toString());
        dto.setDescription(fields[4].toString());
        dto.setRating(Double.parseDouble(fields[5].toString()));
        dto.setStudentsReviewed(Long.parseLong(fields[6].toString()));
        dto.setStudentsRegistered(Long.parseLong(fields[7].toString()));
        dto.setDurationHours(Integer.parseInt(fields[8].toString()));
        dto.setLevel(CourseLevel.valueOf(fields[9].toString()));
        dto.setImgUrl(fields[10].toString());
        dto.setCourseStatus(CourseStatus.valueOf(fields[11].toString()));
        dto.setSkills(SET_CONVERTER.convertToEntityAttribute(fields[12].toString()));
        dto.setLanguages(SET_CONVERTER.convertToEntityAttribute(fields[13].toString()));
        return dto;
    }
}
