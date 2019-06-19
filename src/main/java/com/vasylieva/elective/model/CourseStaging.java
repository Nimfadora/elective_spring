package com.vasylieva.elective.model;

import com.vasylieva.elective.model.dto.CourseStagingDTO;
import com.vasylieva.elective.model.status.CourseLevel;
import com.vasylieva.elective.model.status.CourseStatus;
import com.vasylieva.elective.model.status.Language;
import com.vasylieva.elective.util.SetJsonConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "course_staging")
public class CourseStaging {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Language lang;
    private Long authorId;
    private String category;
    private String title;
    private String description;
    private double durationHours;
    private CourseLevel level;
    private String imgUrl;
    private String courseStatus;
    @Convert(converter = SetJsonConverter.class)
    private Set<String> skills;
    @Convert(converter = SetJsonConverter.class)
    private Set<String> languages;

    /* Only for JPA*/
    public CourseStaging() {
    }

    public CourseStaging(Long authorId, Language lang, String category, String title, String description,
                         double durationHours, CourseLevel level, String imgUrl, String courseStatus,
                         Set<String> skills, Set<String> languages) {
        this.lang = lang;
        this.authorId = authorId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.durationHours = durationHours;
        this.level = level;
        this.imgUrl = imgUrl;
        this.courseStatus = courseStatus;
        this.skills = skills;
        this.languages = languages;
    }

    public CourseStaging(CourseStagingDTO dto, UserDetails user) {
        this.lang = dto.lang;
        this.authorId = user.getId();
        this.category = dto.category;
        this.title = dto.title;
        this.description = dto.description;
        this.durationHours = dto.durationHours;
        this.level = dto.level;
        this.imgUrl = dto.imgUrl;
        this.courseStatus = CourseStatus.IN_DEVELOPMENT.toString();
        this.skills = dto.skills;
        this.languages = dto.languages;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        this.durationHours = durationHours;
    }

    public CourseLevel getLevel() {
        return level;
    }

    public void setLevel(CourseLevel level) {
        this.level = level;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    @Override
    public String toString() {
        return "CourseStaging{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", durationHours=" + durationHours +
                ", level=" + level +
                ", imgUrl='" + imgUrl + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", skills=" + skills +
                ", languages=" + languages +
                '}';
    }
}
