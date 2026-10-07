package com.studentperformance.model;

import java.sql.Date;

public class Attendance {

    private int id;
    private int studentId;
    private Date attendanceDate;
    private String status;

    public Attendance() {
    }

    public Attendance(
            int studentId,
            Date attendanceDate,
            String status) {

        this.studentId = studentId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}