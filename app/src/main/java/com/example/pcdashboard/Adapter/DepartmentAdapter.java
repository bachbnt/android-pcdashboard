package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DepartmentPost> departmentPosts;

    public DepartmentAdapter(Context context, ArrayList<DepartmentPost> departmentPosts) {
        this.context = context;
        this.departmentPosts = departmentPosts;
    }

    public void updateList(ArrayList<DepartmentPost> departmentPosts){
        this.departmentPosts=departmentPosts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_department_post,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DepartmentPost departmentPost = departmentPosts.get(position);
        holder.tvTitle.setText(departmentPost.getTitle());
        holder.tvTime.setText(departmentPost.getTime());
        Log.i("tag","onBindViewHolder "+position);
        holder.tvContent.setText(departmentPost.getContent());
        Glide.with(context).load(Uri.parse(departmentPost.getImage())).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return departmentPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvTime,tvContent;
        ImageView ivImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_title_department_item);
            tvTime=itemView.findViewById(R.id.tv_time_department);
            tvContent=itemView.findViewById(R.id.tv_content_department_item);
            ivImage=itemView.findViewById(R.id.iv_image_department_item);
        }
    }
}
