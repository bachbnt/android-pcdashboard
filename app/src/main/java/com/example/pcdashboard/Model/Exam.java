package com.example.pcdashboard.Model;

import com.google.gson.annotations.SerializedName;

public class Exam {
    @SerializedName("name")
    private String subjectName;
    private String time;
    private String place;
    private double score;

    public Exam(String subjectName, String time, String place, double score) {
        this.subjectName = subjectName;
        this.time = time;
        this.place = place;
        this.score = score;
    }


    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
