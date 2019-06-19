package com.vasylieva.elective.model.dto;

import com.vasylieva.elective.model.status.CourseLevel;
import com.vasylieva.elective.model.status.Language;
import com.vasylieva.elective.util.SetJsonConverter;

import javax.persistence.*;
import java.util.Set;

public class CourseStagingDTO {
    public Long id;
    public Language lang;
    public String category;
    public String title;
    public String description;
    public double durationHours;
    public CourseLevel level;
    public String imgUrl;
    public Set<String> skills;
    public Set<String> languages;

    /* Only for JPA*/
    public CourseStagingDTO() {
    }
}
