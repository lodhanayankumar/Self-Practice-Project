package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import booklib.dbconnection;
import get_set.user;

public class userdao {
    public int registerUser(user u) {
        int i = 0;
        try {
            Connection con = dbconnection.getConnection();
            String sql = "INSERT INTO users(username, password, role) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
        
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            i = ps.executeUpdate();
            con.close();
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    public user loginUser(user u) {

        user result = null;

        try {

            Connection con = dbconnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                result = new user();

                result.setId(rs.getInt("id"));
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                result.setRole(rs.getString("role"));
            }
            con.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}