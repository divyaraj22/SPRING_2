package com.div.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.div.constantsURL.Constants;
import com.div.dto.UserDTO;
import com.div.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    public final static Logger logger = Logger.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    @Qualifier("userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(Constants.GET_SIGNUP)
    public String showSignUpForm(Model model, HttpServletRequest request) {
        logger.info("GET request to show sign up form");
        String errMessage = (String) request.getAttribute("err");
        if (errMessage != null) {
            model.addAttribute("err", errMessage);
        }
        Boolean passwordLengthError = (Boolean) request.getAttribute("passwordLengthError");
        if (passwordLengthError != null && passwordLengthError) {
            model.addAttribute("passwordLengthError", true);
        }
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserDTO());
        }
        return Constants.VIEW_SIGNUP;
    }

    @PostMapping(Constants.POST_SIGNUP)
    public String saveUser(@ModelAttribute("user") UserDTO userDto, Model model) {
        logger.info("POST request to save user: " + userDto.getUsername());
        userService.saveUser(userDto);
        return Constants.VIEW_LOGIN;
    }

    @GetMapping(Constants.LOGIN)
    public String showLoginForm(Model model) {
        logger.info("GET request to show login form");
        model.addAttribute("user", new UserDTO());
        return Constants.VIEW_LOGIN;
    }

    @PostMapping(Constants.LOGIN)
    public String loginUser(@RequestParam String email, @RequestParam String password, ModelMap model, HttpSession session) {
        logger.info("POST request to login user with email: " + email);
        UserDTO userDto = userService.findByEmail(email);
        if (userDto == null) {
            logger.warn("Login attempt with invalid email: " + email);
            model.addAttribute("err", Constants.ERR_INVALID_EMAIL);
        } else {
            if (userDto.getPassword().equals(password)) {
                logger.info("User logged in successfully: " + email);
                session.setAttribute("loggedInUser", userDto);
                model.addAttribute("user", userDto);
                return Constants.VIEW_FORM;
            } else {
                logger.warn("Login attempt with invalid password for email: " + email);
                model.addAttribute("err", Constants.ERR_INVALID_PASSWORD);
            }
        }
        return Constants.VIEW_LOGIN;
    }
}