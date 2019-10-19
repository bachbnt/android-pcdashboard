package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Model.Subject;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Subject> subjects;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onEdit(Subject subject);
        void onDelete(Subject subject);
    }

    public SubjectAdapter(Context context, ArrayList<Subject> subjects, OnItemClickListener listener) {
        this.context = context;
        this.subjects = subjects;
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
