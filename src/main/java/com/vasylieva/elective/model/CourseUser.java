package com.vasylieva.elective.model;

import com.vasylieva.elective.model.status.Relationship;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course_user")
public class CourseUser implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private User user;

    @Id
    @ManyToOne
    @JoinColumn
    private Course course;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    /* For JPA only */
    public CourseUser() {
    }

    public CourseUser(User user, Course course, Relationship relationship) {
        this.course = course;
        this.user = user;
        this.relationship = relationship;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseUser{" +
                "user=" + user +
                ", course=" + course +
                ", relationship=" + relationship +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseUser that = (CourseUser) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        return relationship == that.relationship;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (relationship != null ? relationship.hashCode() : 0);
        return result;
    }
}
