package com.example.pcdashboard.Model;

import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("subject")
    private String name;
    private String time;
    private String teacher;
    private String day;

    public Subject() {
    }

    public Subject(String day, String name, String time, String teacher) {
        this.name = name;
        this.time = time;
        this.teacher = teacher;
        this.day=day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
