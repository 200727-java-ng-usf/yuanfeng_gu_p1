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

@WebServlet(name="AdminAddUser", urlPatterns="/AdminAddUser")
public class AdminAddUserServlet extends HttpServlet {
    UserService userService;
    User newUser;

    public AdminAddUserServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  resp.sendRedirect("html/AdminAddUser.html");
        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        newUser = new User();
        int role;
        if(req.getParameter("roleType").equals("Employee")){ role = 2;}else if(req.getParameter("roleType").equals("Manager")){role = 1;
        }else role = 0;


        newUser.setUsername(req.getParameter("username"));
        newUser.setPassword("1234");
        newUser.setFirstName(req.getParameter("firstname"));
        newUser.setLastName(req.getParameter("lastname"));
        newUser.setRole(role);
        newUser.setEmail(req.getParameter("email"));

        userService.addUser(newUser);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String title = "Successfully registered ";
        String goBack = "html/AdminHome.html";

        out.println(
                "<h1 align=\"center\">" + title + "</h1>\n" +
                        " <b>Account username</b>:   "+
                            newUser.getUsername() +
                        " \n<b>Account default password</b>:   "
                        + newUser.getPassword() +
                        " \n<b>User first name</b>:   "
                        + newUser.getFirstName() +
                        " \n<b>User last name</b>:   "
                        + newUser.getLastName() +
                        " \n<b>User Email</b>:"
                        + newUser.getEmail() +
                        " \n<b>User role type</b>:  "
                        + req.getParameter("roleType") +
                        "</ul>\n" +

                        "<br><br><form method='get' action="+goBack+">"+
                        "<button class= 'btn btn-primary' type='submit'><h5>Go back</h5></button>"+
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