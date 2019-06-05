package com.vasylieva.elective.model;

import com.vasylieva.elective.model.status.Role;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String company;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCourse> courseList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    /* For JPA only */
    public User() {
    }

    public User(String name, String email, String company, String password, Role role, List<UserCourse> courseList) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.password = password;
        this.role = role;
        this.courseList = courseList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<UserCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<UserCourse> courseList) {
        this.courseList = courseList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", courseList=" + courseList.stream().map(UserCourse::toString).collect(Collectors.joining(",")) +
                '}';
    }
}
