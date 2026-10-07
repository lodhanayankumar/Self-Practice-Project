package com.studentperformance.model;

public class Mark {

    private int id;
    private int studentId;
    private String subject;
    private String examType;
    private double marks;
    private double maxMarks;

    public Mark() {
    }

    public Mark(
            int studentId,
            String subject,
            String examType,
            double marks,
            double maxMarks) {

        this.studentId = studentId;
        this.subject = subject;
        this.examType = examType;
        this.marks = marks;
        this.maxMarks = maxMarks;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public double getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(double maxMarks) {
        this.maxMarks = maxMarks;
    }
}