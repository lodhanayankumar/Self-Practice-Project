package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import booklib.dbconnection;
import get_set.bookgs;

public class bookdao {

    // add books
    public int addBook(bookgs b) {
    	
        int i = 0;
       
        try {
            Connection con = dbconnection.getConnection();

            String sql = "INSERT INTO books(isbn_no, title, author, publisher, price) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, b.getIsbnNo());
            ps.setString(2, b.getTitle());
            ps.setString(3, b.getAuthor());
            ps.setString(4, b.getPublisher());
            ps.setDouble(5, b.getPrice());

            i = ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return i;
    }


    // VIEW ALL BOOKS
    public List<bookgs> getAllBooks() {

        List<bookgs> list = new ArrayList<>();

        try {

            Connection con = dbconnection.getConnection();

            String sql = "SELECT * FROM books";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                bookgs b = new bookgs();

                b.setIsbnNo(rs.getLong("isbn_no"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPublisher(rs.getString("publisher"));
                b.setPrice(rs.getDouble("price"));

                list.add(b);
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;
    }


    // GET ONE BOOK
    public bookgs getBookById(long isbnNo) {

        bookgs b = null;

        try {

            Connection con = dbconnection.getConnection();

            String sql = "SELECT * FROM books WHERE isbn_no=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, isbnNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                b = new bookgs();

                b.setIsbnNo(rs.getLong("isbn_no"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPublisher(rs.getString("publisher"));
                b.setPrice(rs.getDouble("price"));
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return b;
    }


    // UPDATE BOOK
    public int updateBook(bookgs b) {

        int i = 0;

        try {

            Connection con = dbconnection.getConnection();

            String sql = "UPDATE books SET title=?, author=?, publisher=?, price=? WHERE isbn_no=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getPublisher());
            ps.setDouble(4, b.getPrice());
            ps.setLong(5, b.getIsbnNo());

            i = ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return i;
    }


    // DELETE BOOK
    public int deleteBook(long isbnNo) {

        int i = 0;

        try {

            Connection con = dbconnection.getConnection();

            String sql = "DELETE FROM books WHERE isbn_no=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, isbnNo);

            i = ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return i;
    }


    // SEARCH BOOK
    public List<bookgs> searchBook(String keyword) {

        List<bookgs> list = new ArrayList<>();

        try {

            Connection con = dbconnection.getConnection();

            String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR publisher LIKE ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                bookgs b = new bookgs();

                b.setIsbnNo(rs.getLong("isbn_no"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPublisher(rs.getString("publisher"));
                b.setPrice(rs.getDouble("price"));

                list.add(b);
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;
    }
}