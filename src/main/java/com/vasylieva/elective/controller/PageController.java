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
    public String index(Model model) {
        List<CourseSearchDTO> topRated =  courseService.getCoursesByCategory("Programming", "en")
                .stream()
                .sorted(Comparator.comparing(CourseSearchDTO::getRating).reversed())
                .limit(3)
                .collect(Collectors.toList());
        List<CourseSearchDTO> mostPopular =  courseService.getCoursesByCategory("Programming", "en")
                .stream()
                .sorted(Comparator.comparing(CourseSearchDTO::getStudentsRegistered).reversed())
                .limit(3)
                .collect(Collectors.toList());

        List<CourseSearchDTO> trending =  courseService.getCoursesByCategory("Programming", "en")
                .stream().limit(3).collect(Collectors.toList());

        model.addAttribute("topCourses", topRated);
        model.addAttribute("mostPopular", mostPopular);
        model.addAttribute("trendingCourses", trending);

        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
