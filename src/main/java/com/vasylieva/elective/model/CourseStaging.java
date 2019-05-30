package com.vasylieva.elective.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vasylieva.elective.util.converter.SetJsonConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "course_staging")
public class CourseStaging {
    @Id
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", updatable = false)
    @Fetch(FetchMode.JOIN)
    private User author;

    private String category;
    private String title;

    @Column(length = 2000)
    private String description;
    private double rating;
    private long reviews;
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
    public CourseStaging() {
    }

    public CourseStaging(Course course) {

    }

    public CourseStaging(User author, String category, String title, String description, double rating, long reviews, long studentsRegistered,
                         double durationHours, CourseLevel level, String imgUrl, Set<String> skills,
                         Set<String> languages, String courseStatus) {
        this.author = author;
        this.category = category;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.reviews = reviews;
        this.studentsRegistered = studentsRegistered;
        this.durationHours = durationHours;
        this.level = level;
        this.imgUrl = imgUrl;
        this.skills = skills;
        this.languages = languages;
        this.courseStatus = courseStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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
