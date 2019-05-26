package com.vasylieva.elective.controller;

import com.vasylieva.elective.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private final CourseService courseService;

    @Autowired
    public PageController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/courses")
    public String courses(@RequestParam(name="category") String category, Model model) {
        model.addAttribute("courses", courseService.getCoursesByCategory(category));
        return "courses";
    }
}
