package com.vasylieva.elective.controller;

import com.vasylieva.elective.model.dto.CourseSearchDTO;
import com.vasylieva.elective.service.CourseService;
import com.vasylieva.elective.service.HintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CourseController {

    private final CourseService courseService;
    private final HintService hintService;

    @Autowired
    public CourseController(CourseService courseService, HintService hintService) {
        this.courseService = courseService;
        this.hintService = hintService;
    }

    @RequestMapping(value = "/courses/{category}", method = RequestMethod.GET)
    public String getCoursesByCategory(@PathVariable("category") String category,
                                       @RequestParam(name = "sortBy", required = false, defaultValue = "rating") String sortBy,
                                       @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder,
                                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(name = "lang", required = false, defaultValue = "EN") String lang,
                                       Model model,
                                       Principal principal) {
        List<CourseSearchDTO> courses = courseService.getCoursesByCategory(category, lang.toUpperCase(), sortBy, sortOrder, page);
        model.addAttribute("lang", lang);
        model.addAttribute("categories", CourseService.CATEGORIES);
        model.addAttribute("courses", courses);
        model.addAttribute("user", principal);
        return "courses";
    }

    @RequestMapping(value = "/courses/search", method = RequestMethod.GET)
    public String searchCourses(@RequestParam("searchString") String searchString,
                                @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
                                @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder,
                                @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(name = "lang", required = false, defaultValue = "EN") String lang,
                                Model model,
                                Principal principal) {
        List<CourseSearchDTO> courses = courseService.getCoursesByCategory("Programming", lang, sortBy, sortOrder, page).stream()
                .filter(c -> c.getTitle().toLowerCase().contains(searchString.toLowerCase())).collect(Collectors.toList());
        hintService.addHint(searchString);
        model.addAttribute("lang", lang);
        model.addAttribute("categories", CourseService.CATEGORIES);
        model.addAttribute("courses", courses);
//        model.addAttribute("courses", courseService.findCourses(searchString, lang.toLowerCase(), sortBy, sortOrder, page));
        model.addAttribute("user", principal);
        return "courses";
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public String getCourse(@PathVariable("id") Long id,
                            @RequestParam(name = "lang", required = false, defaultValue = "EN") String lang,
                            Model model,
                            Principal principal) {
        model.addAttribute("lang", lang);
        model.addAttribute("courses", courseService.getCourse(id, lang.toUpperCase()));
        model.addAttribute("categories", CourseService.CATEGORIES);
        model.addAttribute("user", principal);
        return "course";
    }

    @RequestMapping(value = "/courses/create", method = RequestMethod.GET)
    public String getCourseCreationForm(@RequestParam(name = "lang", required = false, defaultValue = "EN") String lang,
                                        Model model,
                                        Principal principal) {
        model.addAttribute("lang", lang);
        model.addAttribute("categories", CourseService.CATEGORIES);
        model.addAttribute("user", principal);
        return "create_course";
    }
}
