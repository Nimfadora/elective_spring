package com.vasylieva.elective.model;

public class CourseSearchDTO {
    private Long id;
    private String category;
    private String title;
    private double rating;
    private long studentsReviewed;
    private long studentsRegistered;
    private double durationHours;
    private CourseLevel level;
    private String imgUrl;
    private CourseStatus courseStatus;

    public CourseSearchDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getRatingInPercents() {
        return (int) (this.rating * 100 / 5.0);
    }
}
