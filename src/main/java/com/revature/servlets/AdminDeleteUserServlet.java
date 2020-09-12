package com.revature.servlets;


import com.revature.models.User;
import com.revature.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="AdminDeleteUser", urlPatterns="/AdminDeleteUser")
public class AdminDeleteUserServlet extends HttpServlet {

    UserService userService;
    User delUser;

    public AdminDeleteUserServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        delUser = new User();
        int role;
        if(req.getParameter("roleType").equals("Employee")){ role = 2;}else if(req.getParameter("roleType").equals("Manager")){role = 1;
        }else role = 0;

        delUser.setUsername(req.getParameter("username"));
        delUser.setPassword("1234");
        delUser.setFirstName(req.getParameter("firstname"));
        delUser.setLastName(req.getParameter("lastname"));
        delUser.setRole(role);

        userService.deleteUser(delUser.getUsername());


        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String title = "Successfully Deleted Account ";
        String goBack = "html/AdminHome.html";

        out.println(
                "<h1 align=\"center\">" + title + "</h1>\n" +
                        "<ul>\n" +
                        "  <li><b>Account username</b>:"
                        + delUser.getUsername() + "\n" +
                        "  <li><b>Account default password</b>:"
                        + delUser.getPassword() + "\n" +
                        "  <li><b>User first name</b>:"
                        + delUser.getFirstName() + "\n" +
                        "  <li><b>User last name</b>:"
                        + delUser.getLastName() + "\n" +
                        "  <li><b>User role type</b>:"
                        + req.getParameter("roleType") + "\n" +
                        "</ul>\n" +

                        "<form method='get' action="+goBack+">"+
                        "<button type='submit'><h5>Go back</h5></button>"+
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
