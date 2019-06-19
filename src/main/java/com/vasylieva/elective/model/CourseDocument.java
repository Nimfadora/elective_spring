package com.vasylieva.elective.model;

import com.vasylieva.elective.model.status.CourseLevel;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Set;

@Document(indexName = "course")
public class CourseDocument {

    @Id
    private Long id;
    private String author;
    private String authorCompany;
    private String category;
    private String title;
    private String description;
    private double durationHours;
    private CourseLevel level;
    private Set<String> skills;
    private Set<String> languages;
    private Set<Feedback> reviews;

    /* Only for JPA*/
    public CourseDocument() {
    }

    public CourseDocument(Course course, User author) {
        this.id = course.getId();
        this.author = author.getName();
        this.authorCompany = author.getCompany();
        this.category = course.getCategory();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.durationHours = course.getDurationHours();
        this.level = course.getLevel();
        this.skills = course.getSkills();
        this.languages = course.getLanguages();
        this.reviews = reviews;
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

    public Set<String> getSkills() {
        return skills;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public String getAuthorCompany() {
        return authorCompany;
    }

    public void setAuthorCompany(String authorCompany) {
        this.authorCompany = authorCompany;
    }

    public Set<Feedback> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Feedback> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "CourseDocument{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", authorCompany='" + authorCompany + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", durationHours=" + durationHours +
                ", level=" + level +
                ", skills=" + skills +
                ", languages=" + languages +
                ", reviews=" + reviews +
                '}';
    }
}
