package com.vasylieva.elective.model;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Set;

@Document(indexName = "course")
public class CourseDocument {

    @Id
    private Long id;

    private String author;

    private String category;
    private String title;
    private String description;
    private double rating;
    private long reviews;
    private long studentsRegistered;
    private double durationHours;
    private CourseLevel level;
    private String imgUrl;
    private String courseStatus;

    private Set<String> skills;
    private Set<String> languages;

    /* Only for JPA*/
    public CourseDocument() {
    }

    public CourseDocument(Course course) {
        this.id = course.getId();
        this.author = course.getAuthor().getUserName();
        this.category = course.getCategory();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.rating = course.getRating();
        this.reviews = course.getStudentsReviewed();
        this.studentsRegistered = course.getStudentsRegistered();
        this.durationHours = course.getDurationHours();
        this.level = course.getLevel();
        this.imgUrl = course.getImgUrl();
        this.skills = course.getSkills();
        this.languages = course.getLanguages();
        this.courseStatus = course.getCourseStatus();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public long getReviews() {
        return reviews;
    }

    public void setReviews(long reviews) {
        this.reviews = reviews;
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
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", reviews=" + reviews +
                ", durationHours=" + durationHours +
                ", level=" + level +
                ", imgUrl='" + imgUrl + '\'' +
                ", skills=" + skills +
                ", languages=" + languages +
                ", courseStatus='" + courseStatus + '\'' +
                '}';
    }

    public int getRatingInPercents() {
        return (int) (this.rating * 100 / 5.0);
    }
}
