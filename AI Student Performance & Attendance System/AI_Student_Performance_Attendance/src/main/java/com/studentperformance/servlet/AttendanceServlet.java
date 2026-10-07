package com.studentperformance.servlet;

import java.io.IOException;
import java.sql.Date;

import com.studentperformance.dao.AttendanceDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int studentId =
                    Integer.parseInt(
                            request.getParameter("studentId")
                    );

            Date attendanceDate =
                    Date.valueOf(
                            request.getParameter(
                                    "attendanceDate"
                            )
                    );

            String status =
                    request.getParameter("status");

            if (!status.equals("PRESENT")
                    && !status.equals("ABSENT")) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/attendance.jsp?error=1"
                );

                return;
            }

            AttendanceDAO dao =
                    new AttendanceDAO();

            boolean result =
                    dao.saveAttendance(
                            studentId,
                            attendanceDate,
                            status
                    );

            if (result) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/attendance.jsp?success=1"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/attendance.jsp?error=1"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/attendance.jsp?error=1"
            );
        }
    }
}