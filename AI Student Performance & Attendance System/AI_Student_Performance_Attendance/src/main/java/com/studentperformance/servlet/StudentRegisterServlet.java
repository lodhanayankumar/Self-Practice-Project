package com.studentperformance.servlet;

import java.io.IOException;

import com.studentperformance.dao.StudentDAO;
import com.studentperformance.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register-student")
public class StudentRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private StudentDAO studentDAO;

    @Override
    public void init() {

        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String course =
                request.getParameter("course");

        String semesterText =
                request.getParameter("semester");

        try {

            int semester =
                    Integer.parseInt(semesterText);

            if (name == null || name.trim().isEmpty()
                    || email == null || email.trim().isEmpty()
                    || course == null || course.trim().isEmpty()
                    || semester < 1 || semester > 12) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/register.jsp?error=invalid"
                );

                return;
            }

            Student student = new Student(
                    name.trim(),
                    email.trim(),
                    course.trim(),
                    semester
            );

            boolean result =
                    studentDAO.addStudent(student);

            if (result) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/students?success=1"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/register.jsp?error=1"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/register.jsp?error=invalid"
            );
        }
    }
}