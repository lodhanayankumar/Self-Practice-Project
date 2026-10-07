<%@ page import="com.studentperformance.model.Student" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>AI Performance Dashboard</title>

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


<%

    Student student =
        (Student) request.getAttribute("student");

    Double attendance =
        (Double) request.getAttribute("attendance");

    Double marks =
        (Double) request.getAttribute("marks");

    String risk =
        (String) request.getAttribute("risk");

    String recommendation =
        (String) request.getAttribute("recommendation");

%>


<div class="page-container">

    <div class="dashboard-header">

        <div>

            <span class="badge">
                AI Performance Analysis
            </span>

            <h1>
                <%= student.getName() %>
            </h1>

            <p>
                <%= student.getCourse() %>
                |
                Semester <%= student.getSemester() %>
            </p>

        </div>

        <a href="students"
           class="btn secondary">

            ← Back to Students

        </a>

    </div>


    <div class="metrics">

        <div class="metric-card">

            <span>
                Attendance
            </span>

            <strong>
                <%= String.format("%.2f", attendance) %>%
            </strong>

            <small>
                Overall attendance
            </small>

        </div>


        <div class="metric-card">

            <span>
                Marks
            </span>

            <strong>
                <%= String.format("%.2f", marks) %>%
            </strong>

            <small>
                Academic percentage
            </small>

        </div>


        <div class="metric-card">

            <span>
                Risk Level
            </span>

            <strong class="risk-<%= risk.toLowerCase() %>">

                <%= risk %>

            </strong>

            <small>
                AI assessment
            </small>

        </div>

    </div>


    <div class="analysis-grid">

        <div class="analysis-card">

            <h2>
                Student Information
            </h2>

            <div class="info-row">

                <span>Student ID</span>

                <strong>
                    <%= student.getId() %>
                </strong>

            </div>


            <div class="info-row">

                <span>Name</span>

                <strong>
                    <%= student.getName() %>
                </strong>

            </div>


            <div class="info-row">

                <span>Email</span>

                <strong>
                    <%= student.getEmail() %>
                </strong>

            </div>


            <div class="info-row">

                <span>Course</span>

                <strong>
                    <%= student.getCourse() %>
                </strong>

            </div>


            <div class="info-row">

                <span>Semester</span>

                <strong>
                    <%= student.getSemester() %>
                </strong>

            </div>

        </div>


        <div class="ai-card">

            <div class="ai-title">

                <span class="robot">
                    🤖
                </span>

                <div>

                    <h2>
                        AI Recommendation
                    </h2>

                    <p>
                        Performance improvement guidance
                    </p>

                </div>

            </div>


            <div class="recommendation">

                <%= recommendation %>

            </div>

        </div>

    </div>


    <div class="summary-card">

        <h2>
            Performance Summary
        </h2>

        <p>

            The student's current attendance is

            <strong>
                <%= String.format("%.2f", attendance) %>%
            </strong>

            and academic performance is

            <strong>
                <%= String.format("%.2f", marks) %>%
            </strong>.

            Based on these values, the system has
            classified the student as

            <strong>
                <%= risk %> RISK
            </strong>.

        </p>

    </div>

</div>

</body>

</html>