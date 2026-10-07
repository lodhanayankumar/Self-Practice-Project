package com.ecommerce.servlet;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        String keyword =
                request.getParameter("keyword");


        if (keyword == null) {

            keyword = "";

        }


        ProductDAO dao =
                new ProductDAO();


        List<Product> products =
                dao.searchProducts(keyword);


        response.setContentType(
                "application/json"
        );

        response.setCharacterEncoding(
                "UTF-8"
        );


        StringBuilder json =
                new StringBuilder();


        json.append("[");


        for (int i = 0;
             i < products.size();
             i++) {


            Product p =
                    products.get(i);


            if (i > 0) {

                json.append(",");

            }


            json.append("{");

            json.append("\"id\":")
                .append(p.getId())
                .append(",");

            json.append("\"name\":\"")
                .append(escapeJson(p.getName()))
                .append("\",");

            json.append("\"category\":\"")
                .append(escapeJson(p.getCategory()))
                .append("\",");

            json.append("\"description\":\"")
                .append(escapeJson(p.getDescription()))
                .append("\",");

            json.append("\"price\":")
                .append(p.getPrice())
                .append(",");

            json.append("\"image\":\"")
                .append(escapeJson(p.getImage()))
                .append("\"");

            json.append("}");

        }


        json.append("]");


        response.getWriter().write(
                json.toString()
        );
    }


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