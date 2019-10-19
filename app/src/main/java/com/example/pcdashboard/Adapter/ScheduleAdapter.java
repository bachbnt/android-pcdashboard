package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Model.Schedule;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
private Context context;
private ArrayList<Schedule> schedules;
private OnItemClickListener listener;

public interface OnItemClickListener{
    void onShow();
    void onHide();
    void onAdd();
}

    public ScheduleAdapter(Context context, ArrayList<Schedule> schedules, OnItemClickListener listener) {
        this.context = context;
        this.schedules = schedules;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
