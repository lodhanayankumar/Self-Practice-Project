package servlet;

import java.io.IOException;

import dao.userdao;
import get_set.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        user u = new user();

        u.setUsername(username);
        u.setPassword(password);

        userdao dao = new userdao();

        user result = dao.loginUser(u);

        if (result != null) {

            Cookie usernameCookie = new Cookie("username", result.getUsername());
            Cookie roleCookie = new Cookie("role", result.getRole());

            response.addCookie(usernameCookie);
            response.addCookie(roleCookie);

            response.sendRedirect("bookList");
        } else {
            response.getWriter().println("Invalid Username or Password");
            response.getWriter().println("<a href='index.html'>Back to Login</a>");
        }
    }
}