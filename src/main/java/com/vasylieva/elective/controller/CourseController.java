package com.vasylieva.elective.controller;

import com.vasylieva.elective.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/courses/{category}", method = RequestMethod.GET)
    public String courses(@PathVariable(name="category") String category,
                          @RequestParam(name="lang") String lang,
                          Model model) {
        model.addAttribute("courses", courseService.getCoursesByCategory(category, lang));
        // add user
        return "courses";
    }

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String courses(@RequestParam(name="id") Long id,
                          @RequestParam(name="lang") String lang,
                          Model model) {
        model.addAttribute("courses", courseService.getCourse(id, lang));
        // add user
        return "course";
    }

}
