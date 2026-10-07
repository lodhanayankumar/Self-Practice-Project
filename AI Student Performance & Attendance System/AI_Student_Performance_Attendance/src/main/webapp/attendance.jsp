<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Attendance - EduAI</title>

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

        <h1>Attendance Management</h1>

        <p class="subtitle">
            Record daily student attendance.
        </p>


        <%
            String success =
                    request.getParameter("success");

            String error =
                    request.getParameter("error");

            if ("1".equals(success)) {
        %>

            <div class="alert success">
                Attendance saved successfully.
            </div>

        <%
            } else if ("1".equals(error)) {
        %>

            <div class="alert error">
                Unable to save attendance.
            </div>

        <%
            }
        %>


        <form action="attendance"
              method="post">

            <label>Student ID</label>

            <input type="number"
                   name="studentId"
                   min="1"
                   required>


            <label>Attendance Date</label>

            <input type="date"
                   name="attendanceDate"
                   required>


            <label>Status</label>

            <select name="status"
                    required>

                <option value="">
                    Select Status
                </option>

                <option value="PRESENT">
                    Present
                </option>

                <option value="ABSENT">
                    Absent
                </option>

            </select>


            <button type="submit"
                    class="btn primary">

                Save Attendance

            </button>

        </form>

    </div>

</div>

</body>

</html>