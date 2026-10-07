package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.model.Customer;

public class CustomerDAO {

    public boolean register(Customer customer) {

        String sql =
                "INSERT INTO customers(name,email,password) VALUES(?,?,?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            System.out.println("Database Connected Successfully");

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());

            int rows = ps.executeUpdate();

            System.out.println("Rows inserted: " + rows);

            return rows > 0;

        } catch (Exception e) {

            System.out.println("========== REGISTRATION ERROR ==========");
            e.printStackTrace();
            System.out.println("=========================================");

            return false;
        }
    }


    public Customer login(String email, String password) {

        String sql =
                "SELECT * FROM customers WHERE email=? AND password=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }

        } catch (Exception e) {

            System.out.println("========== LOGIN ERROR ==========");
            e.printStackTrace();
            System.out.println("=================================");
        }

        return null;
    }
}