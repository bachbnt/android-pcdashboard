package com.example.pcdashboard.Model;

import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("subject")
    private String name;
    private String time;
    private String teacher;

    public Subject(String name, String time, String teacher) {
        this.name = name;
        this.time = time;
        this.teacher = teacher;
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
}
