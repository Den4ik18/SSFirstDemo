package com.web.mainselvlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginController")
public class LoginServlet extends HttpServlet {
    private static final String PATH = "/WEB-INF/views/";
    private static final String REDIRECT = "/com_serve_main_war_exploded/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (userName.equalsIgnoreCase("Denys") && password.equals("admin")) {
            response.sendRedirect(REDIRECT + "main");
        } else {
            response.sendRedirect(REDIRECT);
        }
    }
}
