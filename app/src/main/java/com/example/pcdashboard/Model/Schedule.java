package com.example.pcdashboard.Model;

import java.util.ArrayList;

public class Schedule {
    private String day;
//    private ArrayList<Subject> subjects;
    private String period;
    private String time;
    private String subject;
    private String teacher;

    public Schedule(String day, String period, String time, String subject, String teacher) {
        this.day = day;
        this.period = period;
        this.time = time;
        this.subject = subject;
        this.teacher = teacher;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
