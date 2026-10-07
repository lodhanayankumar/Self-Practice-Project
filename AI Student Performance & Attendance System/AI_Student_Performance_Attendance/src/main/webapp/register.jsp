<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Register Student - EduAI</title>

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

    <div class="form-card">

        <h1>Register Student</h1>

        <p class="subtitle">
            Add a new student to the performance system.
        </p>

        <%
            String error =
                    request.getParameter("error");

            if ("1".equals(error)) {
        %>

            <div class="alert error">
                Student registration failed.
                Email may already exist.
            </div>

        <%
            } else if ("invalid".equals(error)) {
        %>

            <div class="alert error">
                Please enter valid student information.
            </div>

        <%
            }
        %>

        <form action="register-student"
              method="post">

            <label>Student Name</label>

            <input type="text"
                   name="name"
                   placeholder="Enter student name"
                   required>


            <label>Email</label>

            <input type="email"
                   name="email"
                   placeholder="student@example.com"
                   required>


            <label>Course</label>

            <input type="text"
                   name="course"
                   placeholder="B.Tech Computer Science"
                   required>


            <label>Semester</label>

            <input type="number"
                   name="semester"
                   min="1"
                   max="12"
                   placeholder="6"
                   required>


            <button type="submit"
                    class="btn primary">

                Register Student

            </button>

        </form>

    </div>

</div>

</body>
</html>