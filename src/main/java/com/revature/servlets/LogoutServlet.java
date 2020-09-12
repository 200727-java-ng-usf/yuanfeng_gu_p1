package com.revature.servlets;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name="Logout", urlPatterns="/Logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        // 清除数据
        session.invalidate();

        String title = "Successfully Log Out ";
        String goBack = "html/Login.html";


        out.println(
                "<h1 align=\"center\">" + title + "</h1><br>\n" +
                        " <h3>See you Next Time!<h3><br><br> \n\n"+
                        "<form method='get' action="+goBack+">"+
                        "<button type='submit' class= 'btn btn-primary'><h5>Login Again</h5></button>"+
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
