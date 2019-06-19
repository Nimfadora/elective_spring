package com.vasylieva.elective.controller;

import com.vasylieva.elective.model.CourseStaging;
import com.vasylieva.elective.model.UserDetails;
import com.vasylieva.elective.model.dto.CourseStagingDTO;
import com.vasylieva.elective.service.CourseService;
import com.vasylieva.elective.service.HintService;
import com.vasylieva.elective.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class SearchRestController {

    private final CourseService courseService;
    private final UserService userService;
    private final HintService hintService;

    @Autowired
    public SearchRestController(CourseService courseService, UserService userService, HintService hintService) {
        this.courseService = courseService;
        this.userService = userService;
        this.hintService = hintService;
    }

    @RequestMapping(value = "/courses/save", method = RequestMethod.POST)
    public ResponseEntity<String> createCourse(@RequestParam(name = "lang", required = false, defaultValue = "EN") String lang,
                                               @RequestBody List<CourseStagingDTO> courses,
                                               Principal principal) {
        if (courses.size() != 3) {
            return new ResponseEntity<>("Course must be localized to all three languages", HttpStatus.BAD_REQUEST);
        }
        UserDetails user = userService.getUser(principal);
        courses.forEach(c -> courseService.saveCourseToStaging(new CourseStaging(c, user)));

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/search/hints", method = RequestMethod.GET)
    public Set<String> getHints(@RequestParam("searchString") String searchString,
                                @RequestParam(name = "lang", required = false, defaultValue = "en") String lang) {
        return hintService.getHints(searchString, lang.toUpperCase());
    }

    @RequestMapping(value = "/search/results", method = RequestMethod.GET)
    public List<String> getFastResults(@RequestParam("searchString") String searchString,
                                       @RequestParam(name = "lang", required = false, defaultValue = "en") String lang) {
        return courseService.getTopSearchResults(searchString, lang.toUpperCase());
    }
}
