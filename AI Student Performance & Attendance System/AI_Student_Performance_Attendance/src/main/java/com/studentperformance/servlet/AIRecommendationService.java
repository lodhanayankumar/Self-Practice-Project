package com.studentperformance.servlet;

public class AIRecommendationService {

    public static String getRiskLevel(
            double attendance,
            double marks) {

        if (attendance < 60 || marks < 40) {

            return "HIGH";
        }

        if (attendance < 75 || marks < 60) {

            return "MEDIUM";
        }

        return "LOW";
    }

    public static String getRecommendation(
            double attendance,
            double marks) {

        StringBuilder recommendation =
                new StringBuilder();

        if (attendance < 60) {

            recommendation.append(
                    "Attendance is very low. "
                    + "The student should attend classes regularly."
            );

            recommendation.append("\n");
        }

        else if (attendance < 75) {

            recommendation.append(
                    "Attendance needs improvement. "
                    + "The student should maintain better class attendance."
            );

            recommendation.append("\n");
        }

        else {

            recommendation.append(
                    "Attendance is good."
            );

            recommendation.append("\n");
        }

        if (marks < 40) {

            recommendation.append(
                    "Academic performance is weak. "
                    + "The student should focus on basic concepts "
                    + "and meet the faculty for additional guidance."
            );

            recommendation.append("\n");
        }

        else if (marks < 60) {

            recommendation.append(
                    "Marks are average. "
                    + "More practice, revision and regular tests are recommended."
            );

            recommendation.append("\n");
        }

        else {

            recommendation.append(
                    "Academic performance is good. "
                    + "The student should continue the current study routine."
            );

            recommendation.append("\n");
        }

        if (attendance >= 75 && marks >= 60) {

            recommendation.append(
                    "Overall: Keep maintaining the current performance."
            );
        }

        else {

            recommendation.append(
                    "Overall: Create a weekly study plan "
                    + "and monitor attendance and marks regularly."
            );
        }

        return recommendation.toString();
    }
}