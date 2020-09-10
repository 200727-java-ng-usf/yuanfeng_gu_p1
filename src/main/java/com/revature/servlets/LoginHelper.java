package com.revature.servlets;

import com.revature.exceptions.InvalidRequestException;
import com.revature.models.User;
import com.revature.repository.UserRepo;
import com.revature.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginHelper {

    private UserService userService = new UserService();
    private UserRepo userRepo = new UserRepo();

    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvalidRequestException {
        resp.setContentType("text/html");
        PrintWriter out = null;
        out = resp.getWriter();

        String type = req.getParameter("usertype");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        int role = 5;

        if(userRepo.findUserByCredentials(username,password).isPresent()){
            role = userService.authenticate(username,password).getRole();
        }else {
            out.print("Username/Password combination does not exist");
            RequestDispatcher rd = req.getRequestDispatcher("LoginPage.html");
        }

        if (type.equals("Admin") && role == 0 ) {

            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setAttribute("userType", type);
            // expiry in 20 mins
            session.setMaxInactiveInterval(20 * 60);
            resp.sendRedirect("html/AdminHome.html");

        } else if (type.equals("Manager")&& role == 1) {

            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setAttribute("userType", type);
            session.setMaxInactiveInterval(20 * 60);
            resp.sendRedirect("html/ManagerHome.html");

        } else if (type.equals("Employee") && role == 2) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setAttribute("userType", type);
            session.setMaxInactiveInterval(20 * 60);
            resp.sendRedirect("html/EmployeeHome.html");
        } else {
            out.print("<h1>sorry, Your Role type is wrong please Enter again ....</h1>");
            RequestDispatcher rd = req.getRequestDispatcher("html/Login.html");
            try {
                rd.include(req, resp);
            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
              out.close();
        }
    }
}
