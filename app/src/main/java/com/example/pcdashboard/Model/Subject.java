package com.example.pcdashboard.Model;

import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("subject")
    private String name;
    private String time;
    private String teacherOrClass;
    private String day;

    public Subject() {
    }

    public Subject(String name, String time, String teacherOrClass, String day) {
        this.name = name;
        this.time = time;
        this.teacherOrClass = teacherOrClass;
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

    public String getTeacherOrClass() {
        return teacherOrClass;
    }

    public void setTeacherOrClass(String teacherOrClass) {
        this.teacherOrClass = teacherOrClass;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
