package com.studentperformance.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AttendanceDAO {

    // Save attendance
    public boolean saveAttendance(
            int studentId,
            Date attendanceDate,
            String status) {

        String sql =
                "INSERT INTO attendance "
                + "(student_id, attendance_date, status) "
                + "VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE status = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);
            ps.setDate(2, attendanceDate);
            ps.setString(3, status);
            ps.setString(4, status);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // Calculate attendance percentage
    public double getAttendancePercentage(int studentId) {

        String sql =
                "SELECT "
                + "SUM(CASE WHEN status = 'PRESENT' "
                + "THEN 1 ELSE 0 END) AS present_days, "
                + "COUNT(*) AS total_days "
                + "FROM attendance "
                + "WHERE student_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int presentDays =
                        rs.getInt("present_days");

                int totalDays =
                        rs.getInt("total_days");

                if (totalDays == 0) {
                    return 0;
                }

                return (presentDays * 100.0)
                        / totalDays;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}