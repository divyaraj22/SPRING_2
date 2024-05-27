package com.div.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.div.dto.UserDTO;

public class SignupInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals("/signup") && request.getMethod().equalsIgnoreCase("post")) {
            String password = request.getParameter("password");
            if (password.length() < 4) {
                request.setAttribute("passwordLengthError", true);
                request.setAttribute("user", new UserDTO(request.getParameter("username"), password, request.getParameter("email")));
                request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}