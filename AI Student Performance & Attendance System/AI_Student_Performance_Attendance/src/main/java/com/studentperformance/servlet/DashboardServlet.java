package com.studentperformance.servlet;

import java.io.IOException;

import com.studentperformance.dao.AttendanceDAO;
import com.studentperformance.dao.MarksDAO;
import com.studentperformance.dao.StudentDAO;
import com.studentperformance.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String studentIdText =
                    request.getParameter("studentId");

            if (studentIdText == null
                    || studentIdText.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/students"
                );

                return;
            }

            int studentId =
                    Integer.parseInt(studentIdText);

            StudentDAO studentDAO =
                    new StudentDAO();

            AttendanceDAO attendanceDAO =
                    new AttendanceDAO();

            MarksDAO marksDAO =
                    new MarksDAO();

            Student student =
                    studentDAO.getStudentById(studentId);

            if (student == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/students"
                );

                return;
            }

            double attendance =
                    attendanceDAO.getAttendancePercentage(
                            studentId
                    );

            double marks =
                    marksDAO.getAveragePercentage(
                            studentId
                    );

            String risk =
                    AIRecommendationService.getRiskLevel(
                            attendance,
                            marks
                    );

            String recommendation =
                    AIRecommendationService.getRecommendation(
                            attendance,
                            marks
                    );

            request.setAttribute(
                    "student",
                    student
            );

            request.setAttribute(
                    "attendance",
                    attendance
            );

            request.setAttribute(
                    "marks",
                    marks
            );

            request.setAttribute(
                    "risk",
                    risk
            );

            request.setAttribute(
                    "recommendation",
                    recommendation
            );

            request.getRequestDispatcher(
                    "/dashboard.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/students"
            );
        }
    }
}