package com.vasylieva.elective.controller;

import com.vasylieva.elective.model.Course;
import com.vasylieva.elective.model.UserDetails;
import com.vasylieva.elective.model.dto.CourseSearchDTO;
import com.vasylieva.elective.model.status.Language;
import com.vasylieva.elective.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
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
    public String index(@RequestParam(name = "lang", required = false, defaultValue = "EN") String lang,
                        Model model, Principal principal) {
        model.addAttribute("lang", lang);
        if(principal != null) {
            model.addAttribute("user", ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        }
        model.addAttribute("categories", CourseService.CATEGORIES);
        model.addAttribute("topCourses", courseService.getTopRatedCourses(lang.toUpperCase()));
        model.addAttribute("mostPopular", courseService.getMostPopularCourses(lang.toUpperCase()));
        model.addAttribute("trendingCourses", courseService.getTrendingCourses(lang.toUpperCase()));
        return "index";
    }
}
