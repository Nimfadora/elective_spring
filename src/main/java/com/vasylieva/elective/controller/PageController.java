package com.vasylieva.elective.controller;

import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.dto.CourseSearchDTO;
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
    public String index(@RequestParam(name = "lang", required = false, defaultValue = "uk") String lang, Model model) {
        model.addAttribute("topCourses", courseService.getTopRatedCourses(lang));
        model.addAttribute("mostPopular", courseService.getMostPopularCourses(lang));
        model.addAttribute("trendingCourses", courseService.getTrendingCourses(lang));
        return "index";
    }
}
