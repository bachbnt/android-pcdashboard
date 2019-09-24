package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Model.Classroom;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Classroom> classrooms;
    private OnItemClickListener listener;

    public ClassroomAdapter(Context context, ArrayList<Classroom> classrooms, OnItemClickListener listener) {
        this.context = context;
        this.classrooms = classrooms;
        this.listener = listener;
    }

    interface OnItemClickListener {
        void onClick(Classroom classroom);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_classroom_post,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classroom classroom=classrooms.get(position);
        holder.tvName.setText(classroom.getName());
        holder.tvTime.setText(classroom.getTime());
        holder.tvContent.setText(classroom.getContent());
        Glide.with(context).load(Uri.parse(classroom.getAvatar())).into(holder.ivAvatar);
        Glide.with(context).load(Uri.parse(classroom.getImage())).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return classrooms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvTime, tvContent, tvComment;
        ImageView ivAvatar, ivImage;
        ImageButton ibMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_classroom);
            tvTime = itemView.findViewById(R.id.tv_time_classroom);
            tvContent = itemView.findViewById(R.id.tv_content_classroom);
            tvComment = itemView.findViewById(R.id.tv_comment_classroom);
            ivAvatar = itemView.findViewById(R.id.iv_avatar_classroom);
            ivImage = itemView.findViewById(R.id.iv_image_classroom);
            ibMore = itemView.findViewById(R.id.ib_more_classroom);
        }
    }
}
