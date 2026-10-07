package com.studentperformance.servlet;

import java.io.IOException;
import java.util.List;

import com.studentperformance.dao.StudentDAO;
import com.studentperformance.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        StudentDAO studentDAO =
                new StudentDAO();

        List<Student> students =
                studentDAO.getAllStudents();

        request.setAttribute(
                "students",
                students
        );

        request.getRequestDispatcher(
                "/students.jsp"
        ).forward(request, response);
    }
}