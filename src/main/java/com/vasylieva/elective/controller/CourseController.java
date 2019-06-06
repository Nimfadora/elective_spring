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
    public String getCoursesByCategory(@PathVariable("category") String category,
                                       @RequestParam(name = "sortBy", required = false, defaultValue = "rating") String sortBy,
                                       @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder,
                                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam("lang") String lang,
                                       Model model) {
        model.addAttribute("courses", courseService.getCoursesByCategory(category, lang, sortBy, sortOrder, page));
        // add user
        return "courses";
    }

    @RequestMapping(value = "/courses/search", method = RequestMethod.GET)
    public String searchCourses(@RequestParam("searchString") String searchString,
                                @RequestParam(name = "sortBy", required = false, defaultValue = "rating") String sortBy,
                                @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder,
                                @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                @RequestParam("lang") String lang,
                                Model model) {
        model.addAttribute("courses", courseService.findCourses(searchString, lang, sortBy, sortOrder, page));
        // add user
        return "courses";
    }

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String getCourse(@RequestParam(name = "id") Long id,
                            @RequestParam(name = "lang") String lang,
                            Model model) {
        model.addAttribute("courses", courseService.getCourse(id, lang));
        // add user
        return "course";
    }

}
