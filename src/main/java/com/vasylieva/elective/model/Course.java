package com.vasylieva.elective.model;

import com.vasylieva.elective.model.status.CourseLevel;
import com.vasylieva.elective.model.status.Language;
import com.vasylieva.elective.util.SetJsonConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<CourseUser> courseUsers = new HashSet<>();
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Feedback> courseFeedbacks = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Language lang;
    private String category;
    private String title;
    @Column(length = 2000)
    private String description;
    @Column(name = "duration")
    private double durationHours;
    @Enumerated(EnumType.STRING)
    private CourseLevel level;
    private String imgUrl;
    @Column(name = "status")
    private String courseStatus;
    @Convert(converter = SetJsonConverter.class)
    private Set<String> skills;
    @Convert(converter = SetJsonConverter.class)
    private Set<String> languages;

    /* Only for JPA*/
    public Course() {
    }

    public Course(Language lang, String category, String title, String description, double durationHours, CourseLevel level,
                  String imgUrl, Set<String> skills, Set<String> languages, String courseStatus) {
        this.lang = lang;
        this.category = category;
        this.title = title;
        this.description = description;
        this.durationHours = durationHours;
        this.level = level;
        this.imgUrl = imgUrl;
        this.skills = skills;
        this.languages = languages;
        this.courseStatus = courseStatus;
    }

    public Course(CourseStaging courseStaging) {
        this.id = courseStaging.getId();
        this.lang = courseStaging.getLang();
        this.category = courseStaging.getCategory();
        this.title = courseStaging.getTitle();
        this.description = courseStaging.getDescription();
        this.durationHours = courseStaging.getDurationHours();
        this.level = courseStaging.getLevel();
        this.imgUrl = courseStaging.getImgUrl();
        this.skills = courseStaging.getSkills();
        this.languages = courseStaging.getLanguages();
        this.courseStatus = courseStaging.getCourseStatus();
    }

    public Set<Feedback> getCourseFeedbacks() {
        return courseFeedbacks;
    }

    public void setCourseFeedbacks(Set<Feedback> courseFeedbacks) {
        this.courseFeedbacks = courseFeedbacks;
    }

    public Set<CourseUser> getCourseUsers() {
        return courseUsers;
    }

    public void setCourseUsers(Set<CourseUser> courseUsers) {
        this.courseUsers = courseUsers;
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

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
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
        return "Course{" +
                "id=" + id +
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
