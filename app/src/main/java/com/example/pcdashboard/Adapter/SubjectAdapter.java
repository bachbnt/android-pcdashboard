package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Model.Subject;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Subject> subjects;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onEdit(Subject subject);
        void onDelete(Subject subject);
    }

    public SubjectAdapter(Context context, ArrayList<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_subject,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subject subject=subjects.get(position);
        holder.etName.setText(subject.getName());
        holder.etTime.setText(subject.getTime());
        holder.etTeacher.setText(subject.getTeacher());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private EditText etName;
        private EditText etTime;
        private EditText etTeacher;
        private ImageButton ibDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etName=itemView.findViewById(R.id.et_name_subject_item);
            etTime=itemView.findViewById(R.id.et_time_subject_item);
            etTeacher=itemView.findViewById(R.id.et_teacher_subject_item);
            ibDelete=itemView.findViewById(R.id.ib_delete_subject_item);
        }
    }
}
