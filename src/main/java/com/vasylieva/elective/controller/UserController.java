package com.vasylieva.elective.controller;


import com.vasylieva.elective.model.User;
import com.vasylieva.elective.model.status.Relationship;
import com.vasylieva.elective.model.status.Role;
import com.vasylieva.elective.service.CourseService;
import com.vasylieva.elective.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public UserController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(name = "lang") String lang,
                        Model model) {
        model.addAttribute("lang", lang);
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(@RequestParam(name = "lang") String lang,
                         Model model) {
        model.addAttribute("lang", lang);
        return "signup";
    }

    @RequestMapping(value = "/user/courses/{relationship}", method = RequestMethod.GET)
    public String courses(@PathVariable(name = "relationship") String relationship,
                          @RequestParam(name = "lang") String lang,
                          Model model) {
        User user = new User(); // replace by session user
        model.addAttribute("courses", courseService.getCoursesByUserAndRelationship(user, relationship, lang));
        model.addAttribute("lang", lang);
        if(user.getRole() == Role.AUTHOR) {
            return "author";
        }
        if(user.getRole() == Role.STUDENT) {
            return "student";
        }
        throw new IllegalStateException("page not found");
    }

}
