package com.revature.servlets;


import com.revature.exceptions.InvalidRequestException;
import com.revature.repository.UserRepo;
import com.revature.services.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name="Login", urlPatterns="/Login")
public class LoginServlet extends HttpServlet {

    private final LoginHelper loginHelper = new LoginHelper();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            loginHelper.process(req,resp);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
