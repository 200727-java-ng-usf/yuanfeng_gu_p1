package com.revature.servlets;

import com.revature.exceptions.InvalidRequestException;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;


@WebServlet(name="SubmitReimbursement", urlPatterns="/SubmitReimbursement")
@MultipartConfig
public class EmployeeAddReimServlet extends HttpServlet {


    UserService userService;
    ReimbursementService reimbursementService;
    User authUser;
    Reimbursement newReimbursement;

    public EmployeeAddReimServlet() {
        userService = new UserService();
        reimbursementService = new ReimbursementService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String username = String.valueOf(session.getAttribute("user"));

        try {
            authUser = userService.findUserByUsername(username);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        int authId = authUser.getId();
        int reimbursementType;
        if(req.getParameter("reimbursementType").equals("lodging")){ reimbursementType = 1;}else if(req.getParameter("reimbursementType").equals("travel")){reimbursementType = 2;
        }else if(req.getParameter("reimbursementType").equals("food")){reimbursementType = 3;}else reimbursementType = 4;

        newReimbursement = new Reimbursement();

        newReimbursement.setAmount(Double.parseDouble(req.getParameter("amount")));
        newReimbursement.setTypeId(reimbursementType);
        newReimbursement.setStatusId(2);
        newReimbursement.setAuthorId(authId);
        newReimbursement.setDescription(req.getParameter("description"));
        newReimbursement.setDateSubmitted(new Timestamp(System.currentTimeMillis()));

        reimbursementService.addReimbursement(newReimbursement);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String title = "Successfully added New Reimbursement ";
        String goBack = "html/EmployeeHome.html";

        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>Reimbursement Amount</b>:"
                + newReimbursement.getAmount() + "\n" +
                "  <li><b>Reimbursement Type</b>:"
                + req.getParameter("reimbursementType") + "\n" +
                "  <li><b>Reimbursement submitted Date</b>:"
                + newReimbursement.getDateSubmitted() + "\n" +
                "</ul>\n" +

                "<form method='get' action="+goBack+">"+
                "<button type='submit'><h5>Go back</h5></button>"+
                "</form>"+
                "</body></html>");






    }
}
