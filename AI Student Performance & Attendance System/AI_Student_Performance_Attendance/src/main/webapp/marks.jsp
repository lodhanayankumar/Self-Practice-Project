<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Marks - EduAI</title>

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

        <h1>Marks Management</h1>

        <p class="subtitle">
            Add examination marks for students.
        </p>


        <%
            String success =
                    request.getParameter("success");

            String error =
                    request.getParameter("error");

            if ("1".equals(success)) {
        %>

            <div class="alert success">
                Marks added successfully.
            </div>

        <%
            } else if ("1".equals(error)
                    || "invalid".equals(error)) {
        %>

            <div class="alert error">
                Please enter valid marks.
            </div>

        <%
            }
        %>


        <form action="marks"
              method="post">

            <label>Student ID</label>

            <input type="number"
                   name="studentId"
                   min="1"
                   required>


            <label>Subject</label>

            <input type="text"
                   name="subject"
                   placeholder="Java Programming"
                   required>


            <label>Exam Type</label>

            <select name="examType"
                    required>

                <option value="">
                    Select Exam
                </option>

                <option value="Unit Test">
                    Unit Test
                </option>

                <option value="Mid Term">
                    Mid Term
                </option>

                <option value="Final Exam">
                    Final Exam
                </option>

            </select>


            <label>Obtained Marks</label>

            <input type="number"
                   name="marks"
                   min="0"
                   step="0.01"
                   required>


            <label>Maximum Marks</label>

            <input type="number"
                   name="maxMarks"
                   min="1"
                   step="0.01"
                   required>


            <button type="submit"
                    class="btn primary">

                Save Marks

            </button>

        </form>

    </div>

</div>

</body>

</html>