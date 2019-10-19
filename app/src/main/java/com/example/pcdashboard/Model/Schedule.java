package com.example.pcdashboard.Model;

import java.util.ArrayList;

public class Schedule {
    private String day;
    private ArrayList<Subject> subjects;

    public Schedule(String day, ArrayList<Subject> subjects) {
        this.day = day;
        this.subjects = subjects;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
