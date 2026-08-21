package servlet;

import java.io.IOException;

import dao.bookdao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteBook")
public class deletebook extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String role = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("role")) {
                    role = c.getValue();
                    }
            }
        }
        if (!role.equals("AUTHOR") && !role.equals("LIBRARIAN")) {
            response.getWriter().println("<h2>You are not allowed to delete book</h2>");
            return;
        }
        long isbnNo = Long.parseLong(request.getParameter("isbnNo"));

        bookdao dao = new bookdao();

        int i = dao.deleteBook(isbnNo);

        if (i > 0) {
            response.sendRedirect("bookList");
        } else {
            response.getWriter().println("<h2>Book Not Deleted</h2>");
            response.getWriter().println("<a href='bookList'>Back</a>");
        }
    }
}