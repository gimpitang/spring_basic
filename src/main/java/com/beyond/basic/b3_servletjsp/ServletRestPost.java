package com.beyond.basic.b3_servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/rest/post")
public class ServletRestPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //      파라미터로 가정
        String name =req.getParameter("name");
        String email =req.getParameter("email");

        System.out.println(name);
        System.out.println(email);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("OK");
        printWriter.flush();
    }
}
