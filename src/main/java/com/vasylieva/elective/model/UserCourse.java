package com.vasylieva.elective.model;

import com.vasylieva.elective.model.status.Relationship;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class UserCourse implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private User user;

    @Id
    @ManyToOne
    @JoinColumn
    private Course course;

    private Relationship relationship;

    /* For JPA only */
    public UserCourse() {
    }

    public UserCourse(Course course, Relationship relationship) {
        this.course = course;
        this.relationship = relationship;
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

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "user=" + user +
                ", course=" + course +
                ", relationship=" + relationship +
                '}';
    }
}
