package servlet;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.userdao;
import get_set.user;

@WebServlet("/register")
public class ragister extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        user u = new user();

        u.setUsername(username);
        u.setPassword(password);
        u.setRole(role);

        userdao dao = new userdao();

        int i = dao.registerUser(u);

        if (i > 0) {
            response.sendRedirect("index.html");
        } else {
            response.sendRedirect("register.html");
        }
    }
}