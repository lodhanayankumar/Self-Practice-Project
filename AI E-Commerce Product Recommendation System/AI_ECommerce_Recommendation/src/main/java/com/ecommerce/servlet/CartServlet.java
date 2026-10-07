package com.ecommerce.servlet;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    // =========================================
    // GET CART
    // =========================================

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session =
                request.getSession(false);


        response.setContentType(
                "application/json"
        );

        response.setCharacterEncoding(
                "UTF-8"
        );


        // User not logged in
        if (session == null ||
            session.getAttribute("customer") == null) {

            response.getWriter().write(
                    "{\"success\":false," +
                    "\"loginRequired\":true," +
                    "\"message\":\"Please login first.\"}"
            );

            return;
        }


        Customer customer =
                (Customer) session.getAttribute(
                        "customer"
                );


        CartDAO dao =
                new CartDAO();


        List<CartItem> items =
                dao.getCartItems(
                        customer.getId()
                );


        StringBuilder json =
                new StringBuilder();


        json.append("[");


        for (int i = 0;
             i < items.size();
             i++) {


            CartItem item =
                    items.get(i);


            if (i > 0) {

                json.append(",");

            }


            json.append("{");

            json.append("\"id\":")
                .append(item.getId())
                .append(",");

            json.append("\"productId\":")
                .append(item.getProductId())
                .append(",");

            json.append("\"productName\":\"")
                .append(escapeJson(
                        item.getProductName()
                ))
                .append("\",");

            json.append("\"price\":")
                .append(item.getPrice())
                .append(",");

            json.append("\"quantity\":")
                .append(item.getQuantity());

            json.append("}");

        }


        json.append("]");


        response.getWriter().write(
                json.toString()
        );
    }


    // =========================================
    // ADD / REMOVE / UPDATE
    // =========================================

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType(
                "application/json"
        );

        response.setCharacterEncoding(
                "UTF-8"
        );


        HttpSession session =
                request.getSession(false);


        if (session == null ||
            session.getAttribute("customer") == null) {


            response.getWriter().write(
                    "{\"success\":false," +
                    "\"loginRequired\":true," +
                    "\"message\":\"Please login before adding products to cart.\"}"
            );

            return;
        }


        Customer customer =
                (Customer) session.getAttribute(
                        "customer"
                );


        String action =
                request.getParameter("action");


        CartDAO dao =
                new CartDAO();


        // =====================================
        // REMOVE
        // =====================================

        if ("remove".equals(action)) {

            int cartId =
                    Integer.parseInt(
                            request.getParameter(
                                    "cartId"
                            )
                    );


            boolean result =
                    dao.removeItem(
                            customer.getId(),
                            cartId
                    );


            sendResult(
                    response,
                    result,
                    "Product removed from cart.",
                    "Unable to remove product."
            );


            return;
        }


        // =====================================
        // UPDATE
        // =====================================

        if ("update".equals(action)) {

            int cartId =
                    Integer.parseInt(
                            request.getParameter(
                                    "cartId"
                            )
                    );


            int quantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "quantity"
                            )
                    );


            if (quantity < 1) {

                quantity = 1;

            }


            boolean result =
                    dao.updateQuantity(
                            customer.getId(),
                            cartId,
                            quantity
                    );


            sendResult(
                    response,
                    result,
                    "Quantity updated.",
                    "Unable to update quantity."
            );


            return;
        }


        // =====================================
        // ADD TO CART
        // =====================================

        int productId =
                Integer.parseInt(
                        request.getParameter(
                                "productId"
                        )
                );


        int quantity =
                Integer.parseInt(
                        request.getParameter(
                                "quantity"
                        )
                );


        if (quantity < 1) {

            quantity = 1;

        }


        boolean result =
                dao.addToCart(
                        customer.getId(),
                        productId,
                        quantity
                );


        sendResult(
                response,
                result,
                "Product added to cart.",
                "Unable to add product."
        );
    }


    // =========================================
    // SEND JSON RESULT
    // =========================================

    private void sendResult(
            HttpServletResponse response,
            boolean success,
            String successMessage,
            String errorMessage)
            throws IOException {


        if (success) {

            response.getWriter().write(
                    "{\"success\":true," +
                    "\"message\":\"" +
                    successMessage +
                    "\"}"
            );

        } else {

            response.getWriter().write(
                    "{\"success\":false," +
                    "\"message\":\"" +
                    errorMessage +
                    "\"}"
            );

        }
    }


    // =========================================
    // ESCAPE JSON
    // =========================================

    private String escapeJson(String value) {

        if (value == null) {

            return "";

        }


        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}