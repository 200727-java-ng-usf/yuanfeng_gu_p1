package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;


@WebServlet(name="Admin", urlPatterns="/Admin")
public class AdminServlet extends HttpServlet {

    UserService userService;

    public AdminServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //get username form login page
        HttpSession session = req.getSession(false);
        String username = (String)session.getAttribute("user");


        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        ObjectMapper map = new ObjectMapper();

        Set<User> userSet = userService.findAllUser();

        out.print(map.writeValueAsString(userSet));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
