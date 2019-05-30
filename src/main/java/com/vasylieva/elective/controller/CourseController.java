package com.vasylieva.elective.controller;

import com.vasylieva.elective.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CourseController {

    @Autowired
    private CourseService service;

    public void callCourse() {
        service.getCoursesByCategory(null);
        service.getCourse(0);
        service.createCourse(null);
        service.findCourses(null, null, null, null, 0);
    }

}
