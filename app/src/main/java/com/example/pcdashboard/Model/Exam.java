package com.example.pcdashboard.Model;

public class Exam {
    private String subjectId;
    private String subjectName;
    private String classId;
    private String time;
    private String place;
    private double score;

    public Exam(String subjectId, String subjectName, String classId, String time, String place, double score) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.classId = classId;
        this.time = time;
        this.place = place;
        this.score = score;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
