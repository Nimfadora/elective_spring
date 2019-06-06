package com.vasylieva.elective.controller;

import com.vasylieva.elective.service.CourseService;
import com.vasylieva.elective.service.HintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SearchRestController {

    private final CourseService courseService;
    private final HintService hintService;

    @Autowired
    public SearchRestController(CourseService courseService, HintService hintService) {
        this.courseService = courseService;
        this.hintService = hintService;
    }

    @RequestMapping(value = "/search/hints", method = RequestMethod.GET)
    public List<String> getHints(@RequestParam("searchString") String searchString,
                                 @RequestParam("lang") String lang) {
        return courseService.getTopSearchResults(searchString, lang);;
    }

    @RequestMapping(value = "/search/results", method = RequestMethod.GET)
    public Map<String, String> getFastResults(@RequestParam("searchString") String searchString,
                                              @RequestParam("lang") String lang) {
        return hintService.getHints(searchString, lang);
    }
}
