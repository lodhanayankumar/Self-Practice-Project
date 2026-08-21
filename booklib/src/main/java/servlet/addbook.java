package servlet;

import java.io.IOException;
import java.util.Random;

import dao.bookdao;
import get_set.bookgs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addBook")
public class addbook extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        String role = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("role")) role = c.getValue();
            }
        }

        if (!role.equals("AUTHOR") && !role.equals("LIBRARIAN")) {

            response.getWriter().println("<h2>You are not allowed to add book</h2>");
            return;
        }

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        double price = Double.parseDouble(request.getParameter("price"));
 
        Random r = new Random();
        long isbnNo = 1000000000L + r.nextInt(900000000);
       
        bookgs b = new bookgs();
        b.setIsbnNo(isbnNo);
        b.setTitle(title);
        b.setAuthor(author);
        b.setPublisher(publisher);
        b.setPrice(price);
        
        bookdao dao = new bookdao();

        int i = dao.addBook(b);
        if (i > 0) {
            response.sendRedirect("bookList");
        } else {
            response.getWriter().println("Book Not Added");
            response.getWriter().println("<a href='add.html'>Back</a>");
        }
    }
}