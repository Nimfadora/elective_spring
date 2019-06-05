package com.vasylieva.elective.model.dto;

import com.vasylieva.elective.model.status.CourseLevel;
import com.vasylieva.elective.model.status.CourseStatus;

import java.util.Set;

public class CourseDTO {
    private Long id;
    private String authorName;
    private String authorCompany;
    private String title;
    private String description;
    private double rating;
    private long studentsReviewed;
    private long studentsRegistered;
    private double durationHours;
    private CourseLevel level;
    private String imgUrl;
    private CourseStatus courseStatus;
    private Set<String> skills;
    private Set<String> languages;

    public CourseDTO() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorCompany() {
        return authorCompany;
    }

    public void setAuthorCompany(String authorCompany) {
        this.authorCompany = authorCompany;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getStudentsReviewed() {
        return studentsReviewed;
    }

    public void setStudentsReviewed(long studentsReviewed) {
        this.studentsReviewed = studentsReviewed;
    }

    public long getStudentsRegistered() {
        return studentsRegistered;
    }

    public void setStudentsRegistered(long studentsRegistered) {
        this.studentsRegistered = studentsRegistered;
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
        return "CourseDTO{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", authorCompany='" + authorCompany + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", studentsReviewed=" + studentsReviewed +
                ", studentsRegistered=" + studentsRegistered +
                ", durationHours=" + durationHours +
                ", level=" + level +
                ", imgUrl='" + imgUrl + '\'' +
                ", courseStatus=" + courseStatus +
                ", skills=" + skills +
                ", languages=" + languages +
                '}';
    }

    public int getRatingInPercents() {
        return (int) (this.rating * 100 / 5.0);
    }
}
