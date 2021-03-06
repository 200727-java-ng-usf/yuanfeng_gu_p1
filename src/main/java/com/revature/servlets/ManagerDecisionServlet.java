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
import java.util.Set;


@WebServlet(name="ManagerDecision", urlPatterns="/ManagerDecision")
public class ManagerDecisionServlet extends HttpServlet {

    UserService userService;
    ReimbursementService reimbursementService;
    User thurUser;
    Reimbursement reimbursement;
    Set<Reimbursement> reimbursementSet;
    RequestDispatcher rd;

    public ManagerDecisionServlet() {

        reimbursementService = new ReimbursementService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int reimId;
        int statusId;
        int resolverId;
        if(req.getParameter("decision").equals("approve")){ statusId = 1;}else statusId = 0;

        reimId = Integer.parseInt(req.getParameter("id"));

        HttpSession session = req.getSession(false);
        String username = (String)session.getAttribute("user");
        try {
            thurUser = userService.findUserByUsername(username);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        resolverId = thurUser.getId();

        reimbursementService.decisionOfReimbursement(resolverId,statusId,reimId);



        resp.setContentType("text/html");
        PrintWriter out = null;
        out = resp.getWriter();

        if(statusId==1){

            resp.setContentType("text/html");
            String title = "Successfully Submitted ";
            String goBack = "html/ManagerHome.html";

            out.println(
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                            " <h3 align=\"center\">Reimbursement has been approved</h3>\n"+
                            "  <h3 align=\"center\">Reimbursement ID: </h3><h3>"
                            + reimId + "</h3>\n" +

                            "<form method='get' action="+goBack+">"+
                            "<br><button class='btn btn-primary' type='submit'><h3>Go back</h3></button>"+
                            "</form>");
            RequestDispatcher rd = req.getRequestDispatcher("html/BG.html");
            try {
                rd.include(req, resp);
            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            out.close();

        }else {

            resp.setContentType("text/html");
            String title = "Reimbursement rejected ";
            String goBack = "html/ManagerHome.html";

            out.println(
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                            " <h2 align=\"center\">Reimbursement has been rejected</h2>\n"+
                            "  <h2 align=\"center\">Reimbursement ID: </h2><h2>"
                            + reimId + "</h2>\n" +

                            "<form method='get' action="+goBack+">"+
                            "<button type='submit' class='btn btn-primary'><h2>Go back</h2></button>"+
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
