package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.CartItem;

public class CartDAO {

    // =========================================
    // ADD PRODUCT TO CART
    // =========================================

    public boolean addToCart(int customerId,
                             int productId,
                             int quantity) {

        String checkSql =
                "SELECT id, quantity FROM cart " +
                "WHERE customer_id=? AND product_id=?";

        String insertSql =
                "INSERT INTO cart(customer_id, product_id, quantity) " +
                "VALUES(?,?,?)";

        String updateSql =
                "UPDATE cart SET quantity=? WHERE id=?";


        try (Connection con =
                     DBConnection.getConnection()) {

            // Check if product already exists
            try (PreparedStatement ps =
                         con.prepareStatement(checkSql)) {

                ps.setInt(1, customerId);
                ps.setInt(2, productId);

                ResultSet rs =
                        ps.executeQuery();


                if (rs.next()) {

                    int cartId =
                            rs.getInt("id");

                    int oldQuantity =
                            rs.getInt("quantity");

                    int newQuantity =
                            oldQuantity + quantity;


                    try (PreparedStatement update =
                                 con.prepareStatement(updateSql)) {

                        update.setInt(1, newQuantity);
                        update.setInt(2, cartId);

                        return update.executeUpdate() > 0;
                    }

                }
            }


            // Product does not exist in cart
            try (PreparedStatement insert =
                         con.prepareStatement(insertSql)) {

                insert.setInt(1, customerId);
                insert.setInt(2, productId);
                insert.setInt(3, quantity);

                return insert.executeUpdate() > 0;
            }


        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }


    // =========================================
    // GET CART
    // =========================================

    public List<CartItem> getCartItems(int customerId) {

        List<CartItem> cartItems =
                new ArrayList<>();


        String sql =
                "SELECT c.id, c.customer_id, " +
                "c.product_id, p.name, p.price, c.quantity " +
                "FROM cart c " +
                "JOIN products p " +
                "ON c.product_id = p.id " +
                "WHERE c.customer_id=?";


        try (Connection con =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {


            ps.setInt(1, customerId);


            ResultSet rs =
                    ps.executeQuery();


            while (rs.next()) {

                CartItem item =
                        new CartItem(

                                rs.getInt("id"),

                                rs.getInt("customer_id"),

                                rs.getInt("product_id"),

                                rs.getString("name"),

                                rs.getDouble("price"),

                                rs.getInt("quantity")

                        );


                cartItems.add(item);

            }


        } catch (Exception e) {

            e.printStackTrace();

        }


        return cartItems;
    }


    // =========================================
    // REMOVE ITEM
    // =========================================

    public boolean removeItem(int customerId,
                              int cartId) {

        String sql =
                "DELETE FROM cart " +
                "WHERE id=? AND customer_id=?";


        try (Connection con =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {


            ps.setInt(1, cartId);

            ps.setInt(2, customerId);


            return ps.executeUpdate() > 0;


        } catch (Exception e) {

            e.printStackTrace();

        }


        return false;
    }


    // =========================================
    // UPDATE QUANTITY
    // =========================================

    public boolean updateQuantity(int customerId,
                                  int cartId,
                                  int quantity) {

        String sql =
                "UPDATE cart " +
                "SET quantity=? " +
                "WHERE id=? AND customer_id=?";


        try (Connection con =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {


            ps.setInt(1, quantity);

            ps.setInt(2, cartId);

            ps.setInt(3, customerId);


            return ps.executeUpdate() > 0;


        } catch (Exception e) {

            e.printStackTrace();

        }


        return false;
    }
}