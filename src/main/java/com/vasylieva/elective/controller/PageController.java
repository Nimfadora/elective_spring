package com.vasylieva.elective.controller;

import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PageController {

    private final CourseService courseService;

    @Autowired
    public PageController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<Course> topRated =  courseService.getCoursesByCategory("Programming")
                .stream()
                .sorted(Comparator.comparing(Course::getRating).reversed())
                .limit(3)
                .collect(Collectors.toList());
        List<Course> mostPopular =  courseService.getCoursesByCategory("Programming")
                .stream()
                .sorted(Comparator.comparing(Course::getStudentsRegistered).reversed())
                .limit(3)
                .collect(Collectors.toList());

        List<Course> trending =  courseService.getCoursesByCategory("Programming").stream().limit(3).collect(Collectors.toList());

        model.addAttribute("topCourses", topRated);
        model.addAttribute("mostPopular", mostPopular);
        model.addAttribute("trendingCourses", trending);

        return "index";
    }

    @RequestMapping("/courses")
    public String courses(@RequestParam(name="category") String category, Model model) {
        model.addAttribute("courses", courseService.getCoursesByCategory(category));
        return "courses";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
