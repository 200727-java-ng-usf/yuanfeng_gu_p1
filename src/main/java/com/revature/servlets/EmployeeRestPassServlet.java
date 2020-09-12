package com.revature.servlets;

import com.revature.exceptions.InvalidRequestException;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(name="ChangePass", urlPatterns="/ChangePass")
public class EmployeeRestPassServlet extends HttpServlet {

    UserService userService;
    User authUser;

    public EmployeeRestPassServlet() {

        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String username = String.valueOf(session.getAttribute("user"));
        int id = 0;
        String oldPassword;


        try {
            authUser = userService.findUserByUsername(username);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        try {
            id = userService.findUserByUsername(authUser.getUsername()).getId();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
        oldPassword = authUser.getPassword();


        if (req.getParameter("oldPassword").equals(oldPassword)) {

            authUser.setPassword(req.getParameter("password"));

            userService.editUser(authUser.getUsername(), authUser.getPassword(), authUser.getEmail(), authUser.getRole(), id);

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            String title = "Successfully Reset Password ";
            String goBack = "html/EmployeeHome.html";


            out.println(
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                            "  <br><b>Account username</b>:"
                            + authUser.getUsername() +
                            "  <br><b>Account old password</b> :" + oldPassword +
                            "  <br><b>New Password</b> :" + authUser.getPassword() +
                            "<br><form method='get' action=" + goBack + ">" +
                            "<br><br><button class='btn btn-primary' type='submit'><h5>Back To Home Screen</h5></button>" +
                            "</form>");
            RequestDispatcher rd = req.getRequestDispatcher("html/BG.html");
            try {
                rd.include(req, resp);
            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            out.close();
        } else {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            String title = "Your Password does not match the profile ";
            String goBack = "html/EmployeeHome.html";


            out.println(
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                            "  <br><b>Account username</b>:"
                            + authUser.getUsername() +
                            "<br><b>Tips:  Default password is 1234</b> " +
                            "<br><form method='get' action=" + goBack + ">" +
                            "<br><br><button class='btn btn-primary' type='submit'><h5>Back To Home Screen</h5></button>" +
                            "</form>");
            RequestDispatcher rd = req.getRequestDispatcher("html/BG.html");
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

