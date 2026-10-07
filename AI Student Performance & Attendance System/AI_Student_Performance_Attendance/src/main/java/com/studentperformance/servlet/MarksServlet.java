package com.studentperformance.servlet;

import java.io.IOException;

import com.studentperformance.dao.MarksDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/marks")
public class MarksServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int studentId =
                    Integer.parseInt(
                            request.getParameter(
                                    "studentId"
                            )
                    );

            String subject =
                    request.getParameter("subject");

            String examType =
                    request.getParameter("examType");

            double marks =
                    Double.parseDouble(
                            request.getParameter("marks")
                    );

            double maxMarks =
                    Double.parseDouble(
                            request.getParameter("maxMarks")
                    );

            if (subject == null
                    || subject.trim().isEmpty()
                    || marks < 0
                    || maxMarks <= 0
                    || marks > maxMarks) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/marks.jsp?error=invalid"
                );

                return;
            }

            MarksDAO dao =
                    new MarksDAO();

            boolean result =
                    dao.addMark(
                            studentId,
                            subject.trim(),
                            examType,
                            marks,
                            maxMarks
                    );

            if (result) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/marks.jsp?success=1"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/marks.jsp?error=1"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/marks.jsp?error=1"
            );
        }
    }
}