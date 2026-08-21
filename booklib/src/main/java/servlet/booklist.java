package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.bookdao;
import get_set.bookgs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookList")
public class booklist extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        String username = "";
        String role = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    username = c.getValue();
                }
                if (c.getName().equals("role")) {
                    role = c.getValue();
                }
            }
        }

        boolean editbook= false;

        if (role.equals("AUTHOR") || role.equals("LIBRARIAN")) {
            editbook = true;
        }
        bookdao dao = new bookdao();

        List<bookgs> list = dao.getAllBooks();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Library Management System</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Library Management System</h1>");
        out.println("<h2>Welcome, " + username + " (" + role + ")</h2>");
        out.println("<form action='searchBook' method='get'>");
        out.println("<input type='text' name='keyword' ");
        out.println("placeholder='Search by title, author or publisher'>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form>");

        if (editbook) {
            out.println("<br>");
            out.println("<a href='add.html'>");
            out.println("<button>Add Book</button>");
            out.println("</a>");
        }
        out.println("<br><br>");
        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr>");
        out.println("<th>ISBN</th>");
        out.println("<th>Title</th>");
        out.println("<th>Author</th>");
        out.println("<th>Publisher</th>");
        out.println("<th>Price</th>");

        if (editbook) {
            out.println("<th>Action</th>");
        }
        out.println("</tr>");
        
        for (bookgs b : list) {
            out.println("<tr>");
            out.println("<td>" + b.getIsbnNo() + "</td>");
            out.println("<td>" + b.getTitle() + "</td>");
            out.println("<td>" + b.getAuthor() + "</td>");
            out.println("<td>" + b.getPublisher() + "</td>");
            out.println("<td>" + b.getPrice() + "</td>");
//update delete
            if (editbook) {
                out.println("<td>");
                out.println("<a href='updateBook?isbnNo="+ b.getIsbnNo()+ "'>Update</a>");
                out.println(" | "+"<a href='deleteBook?isbnNo="+ b.getIsbnNo()+ "' "
                        + "onclick=\"return confirm('Are you sure you want to delete this book?');\">Delete</a>");
                out.println("</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>"+"</body>"+"</html>");
    }
}