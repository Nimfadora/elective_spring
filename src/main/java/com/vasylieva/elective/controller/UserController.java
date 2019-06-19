package com.vasylieva.elective.controller;


import com.vasylieva.elective.model.User;
import com.vasylieva.elective.model.UserDetails;
import com.vasylieva.elective.model.dto.CourseSearchDTO;
import com.vasylieva.elective.model.status.CourseStatus;
import com.vasylieva.elective.model.status.Role;
import com.vasylieva.elective.service.CourseService;
import com.vasylieva.elective.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final CourseService courseService;
    private final UserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(CourseService courseService, UserService userService, PasswordEncoder encoder) {
        this.courseService = courseService;
        this.userService = userService;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(name = "lang", required = false, defaultValue = "en") String lang,
                               @RequestParam(name = "error", required = false, defaultValue = "false") boolean error,
                               Model model) {
        if (error) {
            model.addAttribute("lang", lang);
            model.addAttribute("error", "Invalid email or password");
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getSignupPage(@RequestBody MultiValueMap<String, String> formData,
                                @RequestParam(name = "lang", required = false, defaultValue = "en") String lang,
                                Model model) {
        User user = new User();
        user.setEmail(formData.getFirst("email"));
        user.setPassword(encoder.encode(formData.getFirst("password")));
        user.setCompany(formData.getFirst("company"));
        user.setName(formData.getFirst("name"));
        Role role = formData.getFirst("role") == null ? Role.STUDENT : Role.AUTHOR;
        user.setRole(role);

        userService.createUser(user);
        model.addAttribute("lang", lang);
        return "redirect:/login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(@RequestParam(name = "lang", required = false, defaultValue = "en") String lang,
                         Model model) {
        model.addAttribute("lang", lang);
        return "signup";
    }

    @RequestMapping(value = "/user/courses", method = RequestMethod.GET)
    public String userCourses(@RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "lang", required = false, defaultValue = "en") String lang,
                              Model model,
                              Principal principal) {
        UserDetails user = userService.getUser(principal);
        CourseStatus courseStatus = CourseStatus.parseCourseStatus(status, user.getRole());
        List<CourseSearchDTO> userCourses = courseService.getCoursesByUserAndRelationship(user, courseStatus,
                lang.toUpperCase());

        model.addAttribute("courses", userCourses);
        model.addAttribute("lang", lang);
        model.addAttribute("user", user);
        model.addAttribute("status", courseStatus);
        return "user_courses";
    }
}
