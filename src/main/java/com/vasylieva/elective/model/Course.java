package com.vasylieva.elective.model;

import com.vasylieva.elective.util.converter.SetJsonConverter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    private Long id;
    private Long authorId;
    private String category;
    private String title;
    @Column(length = 2000)
    private String description;
    private double rating;
    private long studentsReviewed;
    private long studentsRegistered;
    private double durationHours;
    private CourseLevel level;
    private String imgUrl;
    private String courseStatus;
    @Convert(converter = SetJsonConverter.class)
    private Set<String> skills;
    @Convert(converter = SetJsonConverter.class)
    private Set<String> languages;

    /* Only for JPA*/
    public Course() {
    }

    public Course(long authorId, String category, String title, String description, double rating, long studentsReviewed,
                  long studentsRegistered, double durationHours, CourseLevel level, String imgUrl, Set<String> skills,
                  Set<String> languages, String courseStatus) {
        this.authorId = authorId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.studentsReviewed = studentsReviewed;
        this.studentsRegistered = studentsRegistered;
        this.durationHours = durationHours;
        this.level = level;
        this.imgUrl = imgUrl;
        this.skills = skills;
        this.languages = languages;
        this.courseStatus = courseStatus;
    }

    public Course(CourseStaging courseStaging) {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
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
        return "Course{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", studentsReviewed=" + studentsReviewed +
                ", studentsRegistered=" + studentsRegistered +
                ", durationHours=" + durationHours +
                ", level=" + level +
                ", imgUrl='" + imgUrl + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", skills=" + skills +
                ", languages=" + languages +
                '}';
    }

    public int getRatingInPercents() {
        return (int) (this.rating * 100 / 5.0);
    }
}
