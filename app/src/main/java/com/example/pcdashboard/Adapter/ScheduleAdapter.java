package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Model.Schedule;
import com.example.pcdashboard.R;

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

    public ScheduleAdapter(Context context, ArrayList<Schedule> schedules) {
        this.context = context;
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_schedule,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule=schedules.get(position);
        holder.tvDay.setText(schedule.getDay());
        SubjectAdapter subjectAdapter=new SubjectAdapter(context,schedule.getSubjects());
        holder.rvSubjects.setAdapter(subjectAdapter);
        holder.rvSubjects.setLayoutManager(new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    private TextView tvDay;
    private RecyclerView rvSubjects;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay=itemView.findViewById(R.id.tv_day_schedule_item);
            rvSubjects=itemView.findViewById(R.id.recycler_view_subjects);
        }
    }
}
