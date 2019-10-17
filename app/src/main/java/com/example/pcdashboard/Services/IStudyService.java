package com.example.pcdashboard.Services;

import com.example.pcdashboard.Model.Schedule;
import com.example.pcdashboard.Model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IStudyService {
    @GET("schedule/{classId}")
    Call<ArrayList<Schedule>> getSchedule (@Header("Authorization") String token, @Path("classId") String classId);
}
