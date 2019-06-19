package com.vasylieva.elective.model;

import com.vasylieva.elective.util.ListJsonConverter;
import com.vasylieva.elective.util.SetJsonConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private User user;

    @Id
    @ManyToOne
    @JoinColumn
    private Course course;

    private int rating;
    private String feedback;

    @Convert(converter = ListJsonConverter.class)
    private List<String> answers;

    public Feedback() {
    }

    public Feedback(Course course, int rating, String feedback, List<String> answers) {
        this.course = course;
        this.rating = rating;
        this.feedback = feedback;
        this.answers = answers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback1 = (Feedback) o;

        if (rating != feedback1.rating) return false;
        if (user != null ? !user.equals(feedback1.user) : feedback1.user != null) return false;
        if (course != null ? !course.equals(feedback1.course) : feedback1.course != null) return false;
        if (feedback != null ? !feedback.equals(feedback1.feedback) : feedback1.feedback != null) return false;
        return answers != null ? answers.equals(feedback1.answers) : feedback1.answers == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }
}
