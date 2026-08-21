package servlet;

import java.io.IOException;

import dao.bookdao;
import get_set.bookgs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateBook")
public class updatebook extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String role = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("role")) {
                    role = c.getValue();}
            }
        }

        if (!role.equals("AUTHOR") && !role.equals("LIBRARIAN")) {
            response.getWriter().println("You are not allowed to updat book");
            return;
        }

        long isbnNo = Long.parseLong(request.getParameter("isbnNo"));

        bookdao dao = new bookdao();

        bookgs b = dao.getBookById(isbnNo);

        response.setContentType("text/html");

        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Update Book</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Library Management System</h1>");
        response.getWriter().println("<h2>Update Book</h2>");
        response.getWriter().println("<form action='updateBook' method='post'>");
        response.getWriter().println("ISBN No: "+"<input type='text' name='isbnNo' value='" + b.getIsbnNo()+ "' readonly><br><br>" );
        response.getWriter().println("Title: "+"<input type='text' name='title' value='" + b.getTitle()+ "' required><br><br>");
        response.getWriter().println("Author: "+"<input type='text' name='author' value='"+ b.getAuthor()+ "' required><br><br>");
        response.getWriter().println("Publisher: "+"<input type='text' name='publisher' value='"+ b.getPublisher()+ "' required><br><br>");
        response.getWriter().println("Price: "+"<input type='number' step='0.01' name='price' value='"+ b.getPrice()+ "' required><br><br>");
        response.getWriter().println("<input type='submit' value='Update Book'>"+"</form>");
        response.getWriter().println("<br>"+"<a href='bookList'>Back</a>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            response.getWriter().println("<h2>You are not allowed to update book</h2>");
            return;
        }

        long isbnNo = Long.parseLong(request.getParameter("isbnNo"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");

        double price = Double.parseDouble(request.getParameter("price") );

        bookgs b = new bookgs();

        b.setIsbnNo(isbnNo);
        b.setTitle(title);
        b.setAuthor(author);
        b.setPublisher(publisher);
        b.setPrice(price);

        bookdao dao = new bookdao();

        int i = dao.updateBook(b);
        
        if (i > 0) {
            response.sendRedirect("bookList");
        } else {
            response.getWriter().println("Book Not Updated");
            response.getWriter().println("<a href='bookList'>Back</a>");
        }
    }
}