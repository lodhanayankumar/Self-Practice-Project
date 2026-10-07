package com.studentperformance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MarksDAO {

    // Add marks
    public boolean addMark(
            int studentId,
            String subject,
            String examType,
            double marks,
            double maxMarks) {

        String sql =
                "INSERT INTO marks "
                + "(student_id, subject, exam_type, marks, max_marks) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);
            ps.setString(2, subject);
            ps.setString(3, examType);
            ps.setDouble(4, marks);
            ps.setDouble(5, maxMarks);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // Calculate average percentage
    public double getAveragePercentage(int studentId) {

        String sql =
                "SELECT "
                + "COALESCE(SUM(marks), 0) AS total_marks, "
                + "COALESCE(SUM(max_marks), 0) AS total_max "
                + "FROM marks "
                + "WHERE student_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double totalMarks =
                        rs.getDouble("total_marks");

                double totalMax =
                        rs.getDouble("total_max");

                if (totalMax == 0) {
                    return 0;
                }

                return (totalMarks * 100.0)
                        / totalMax;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}