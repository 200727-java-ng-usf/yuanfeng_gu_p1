package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
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


@WebServlet(name="EmployeePending", urlPatterns="/EmployeePending")
public class EmployeePendingReimServlet extends HttpServlet {

    UserService userService;
    ReimbursementService reimbursementService;
    User thurUser;
    Reimbursement reimbursement;

    public EmployeePendingReimServlet() {
        userService = new UserService();
        reimbursementService = new ReimbursementService();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get username form login page
        HttpSession session = req.getSession(false);
        String username = (String)session.getAttribute("user");


        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        ObjectMapper map = new ObjectMapper();

        try {
            thurUser = userService.findUserByUsername(username);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        Set<Reimbursement> reimbursementSet = reimbursementService.findAllPendingReimbursementsForIndividual(thurUser.getId());


        out.print(map.writeValueAsString(reimbursementSet));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
