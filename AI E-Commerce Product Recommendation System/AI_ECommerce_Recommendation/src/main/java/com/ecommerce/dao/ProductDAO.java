package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Product;

public class ProductDAO {

    public List<Product> getAllProducts() {

        List<Product> products =
                new ArrayList<>();

        String sql =
                "SELECT * FROM products";

        try (
            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            while (rs.next()) {

                Product p =
                        new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("image")
                        );

                products.add(p);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return products;
    }


    public List<Product> searchProducts(
            String keyword) {

        List<Product> products =
                new ArrayList<>();

        String sql =
                "SELECT * FROM products " +
                "WHERE name LIKE ? " +
                "OR category LIKE ?";

        try (
            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)
        ) {

            String search =
                    "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Product p =
                        new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("image")
                        );

                products.add(p);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return products;
    }
}