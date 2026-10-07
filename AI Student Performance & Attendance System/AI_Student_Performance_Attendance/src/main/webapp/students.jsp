<%@ page import="java.util.List" %>
<%@ page import="com.studentperformance.model.Student" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Students - EduAI</title>

    <link rel="stylesheet"
          href="css/style.css">

</head>

<body>

<nav class="navbar">

    <div class="logo">
        EduAI
    </div>

    <div class="nav-links">

        <a href="index.jsp">Home</a>

        <a href="students">Students</a>

        <a href="register.jsp">Register</a>

        <a href="attendance.jsp">Attendance</a>

        <a href="marks.jsp">Marks</a>

    </div>

</nav>


<div class="page-container">

    <div class="page-header">

        <div>

            <h1>Students</h1>

            <p>
                View all registered students.
            </p>

        </div>

        <a href="register.jsp"
           class="btn primary">

            + Add Student

        </a>

    </div>


    <%
        String success =
                request.getParameter("success");

        if ("1".equals(success)) {
    %>

        <div class="alert success">
            Student registered successfully.
        </div>

    <%
        }
    %>


    <div class="table-card">

        <table>

            <thead>

                <tr>

                    <th>ID</th>

                    <th>Name</th>

                    <th>Email</th>

                    <th>Course</th>

                    <th>Semester</th>

                    <th>Analysis</th>

                </tr>

            </thead>


            <tbody>

            <%
            List<Student> students =
            (List<Student>) request.getAttribute("students");
                if (students != null
                        && !students.isEmpty()) {

                    for (Student student : students) {
            %>

                <tr>

                    <td>
                        <%= student.getId() %>
                    </td>

                    <td>
                        <strong>
                            <%= student.getName() %>
                        </strong>
                    </td>

                    <td>
                        <%= student.getEmail() %>
                    </td>

                    <td>
                        <%= student.getCourse() %>
                    </td>

                    <td>
                        Semester
                        <%= student.getSemester() %>
                    </td>

                    <td>

                        <a class="small-btn"
                           href="dashboard?studentId=<%= student.getId() %>">

                            View Analysis

                        </a>

                    </td>

                </tr>

            <%
                    }

                } else {
            %>

                <tr>

                    <td colspan="6"
                        class="empty">

                        No students found.

                    </td>

                </tr>

            <%
                }
            %>

            </tbody>

        </table>

    </div>

</div>

</body>

</html>