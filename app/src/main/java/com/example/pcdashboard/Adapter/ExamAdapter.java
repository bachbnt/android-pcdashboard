package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Model.Exam;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Exam> exams;

    public ExamAdapter(Context context, ArrayList<Exam> exams) {
        this.context = context;
        this.exams = exams;
    }
    public void update(ArrayList<Exam> exams){
        this.exams=exams;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_exam,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exam exam=exams.get(position);
        holder.tvSubjectName.setText(exam.getSubjectName());
        holder.tvScore.setText(String.valueOf(exam.getScore()));
        holder.tvTime.setText(exam.getTime());
        holder.tvPlace.setText(exam.getPlace());
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSubjectName;
        private TextView tvScore;
        private TextView tvTime;
        private TextView tvPlace;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName=itemView.findViewById(R.id.tv_name_exam_item);
            tvScore=itemView.findViewById(R.id.tv_score_exam_item);
            tvTime=itemView.findViewById(R.id.tv_time_exam_item);
            tvPlace=itemView.findViewById(R.id.tv_place_exam_item);
        }
    }
}
