package com.revature.servlets;

import com.revature.exceptions.InvalidRequestException;
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


@WebServlet(name="AdminEditUser", urlPatterns="/AdminEditUser")
public class AdminEditUserServlet extends HttpServlet {

    UserService userService;
    User editUser;
    RequestDispatcher rd;

    public AdminEditUserServlet() {

        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("id"));
        resp.setContentType("text/html");
        PrintWriter out = null;
        out = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));

        editUser = userService.findUserById(id);

        out.print(  "<ul>\n" +
                "  <li><b>Account username</b>:"
                + editUser.getUsername() + "\n" +
                "  <li><b>Account default password</b>:"
                + editUser.getPassword() + "\n" +
                "  <li><b>User first name</b>:"
                + editUser.getFirstName() + "\n" +
                "  <li><b>User last name</b>:"
                + editUser.getLastName() + "\n" +
                "  <li><b>User Email</b>:"
                + editUser.getEmail() + "\n" );

        rd = req.getRequestDispatcher("html/AdminEditUser.html");
        try {
            rd.include(req, resp);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.close();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        editUser = new User();
        int id = 0 ;
        int role;
        if(req.getParameter("roleType").equals("Employee")){ role = 2;}else if(req.getParameter("roleType").equals("Manager")){role = 1;
        }else role = 0;


        editUser.setUsername(req.getParameter("username"));
        editUser.setPassword("1234");
        editUser.setFirstName(req.getParameter("firstname"));
        editUser.setLastName(req.getParameter("lastname"));
        editUser.setRole(role);
        editUser.setEmail(req.getParameter("email"));

        try {
            id = userService.findUserByUsername(editUser.getUsername()).getId();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        userService.editUser(editUser.getUsername(),editUser.getPassword(),editUser.getEmail(),editUser.getRole(),id);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String title = "Successfully Edited User Infomation ";
        String goBack = "html/AdminHome.html";


        out.println(
                "<h1 align=\"center\">" + title + "</h1>\n" +
                        "<ul>\n" +
                        "  <li><b>Account username</b>:"
                        + editUser.getUsername() + "\n" +
                        "  <li><b>Account default password</b>:"
                        + editUser.getPassword() + "\n" +
                        "  <li><b>User first name</b>:"
                        + editUser.getFirstName() + "\n" +
                        "  <li><b>User last name</b>:"
                        + editUser.getLastName() + "\n" +
                        "  <li><b>User Email</b>:"
                        + editUser.getEmail() + "\n" +
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
