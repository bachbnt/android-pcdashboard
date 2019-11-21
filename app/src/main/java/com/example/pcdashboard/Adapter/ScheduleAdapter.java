package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Model.Schedule;
import com.example.pcdashboard.Model.Subject;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> implements SubjectAdapter.OnItemClickListener {
    private Context context;
    private ArrayList<Schedule> schedules;
    private OnItemClickListener listener;

    @Override
    public void onEdit(Subject subject, int position, String name, String time, String teacher) {
        listener.onEdit(subject,position,name,time,teacher);
    }

    @Override
    public void onDelete(Subject subject) {
            listener.onDelete(subject);
    }

    public interface OnItemClickListener {
        void onAdd(Schedule schedule, int position);
        void onEdit(Subject subject,int position,String name, String time, String teacher);
        void onDelete(Subject subject);
    }

    public ScheduleAdapter(Context context, ArrayList<Schedule> schedules, OnItemClickListener listener) {
        this.context = context;
        this.schedules = schedules;
        this.listener = listener;
    }

    public void update(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Schedule schedule = schedules.get(position);
        holder.tvDay.setText(schedule.getDay());
        SubjectAdapter subjectAdapter = new SubjectAdapter(context, schedule.getSubjects(), this);
        holder.rvSubjects.setAdapter(subjectAdapter);
        holder.rvSubjects.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        holder.ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ibAdd.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                listener.onAdd(schedule, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDay;
        private RecyclerView rvSubjects;
        private ImageButton ibAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day_schedule_item);
            rvSubjects = itemView.findViewById(R.id.recycler_view_subjects);
            ibAdd = itemView.findViewById(R.id.ib_add_schedule_item);
        }
    }
}
