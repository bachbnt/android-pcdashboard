package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.PostComment;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PostComment> postComments;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEdit(PostComment postComment);

        void onDelete(PostComment postComment);
    }

    public CommentAdapter(Context context, ArrayList<PostComment> postComments, OnItemClickListener listener) {
        this.context = context;
        this.postComments = postComments;
        this.listener = listener;
    }

    public void updateList(ArrayList<PostComment> postComments) {
        this.postComments = postComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_comment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PostComment postComment = postComments.get(position);
        Glide.with(context).load(Uri.parse(postComment.getUserAvatar())).centerCrop().override(40, 40).into(holder.ivAvatar);
        holder.tvName.setText(postComment.getUserName());
        holder.tvContent.setText(postComment.getContent());
        holder.tvTime.setText(postComment.getTime());
        if (postComment.getUserId().equals(SharedPreferencesUtils.loadSelf(context).getId())) {
            holder.ibEdit.setVisibility(View.VISIBLE);
            holder.ibDelete.setVisibility(View.VISIBLE);
            holder.ibEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.ibEdit.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                    listener.onEdit(postComment);
                }
            });
            holder.ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.ibDelete.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                    listener.onDelete(postComment);
                }
            });
        } else {
            holder.ibEdit.setVisibility(View.GONE);
            holder.ibDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return postComments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvName, tvContent, tvTime;
        ImageButton ibEdit, ibDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar_comment_item);
            tvName = itemView.findViewById(R.id.tv_name_comment_item);
            tvContent = itemView.findViewById(R.id.tv_content_comment_item);
            tvTime = itemView.findViewById(R.id.tv_time_comment_item);
            ibEdit = itemView.findViewById(R.id.ib_edit_comment_item);
            ibDelete = itemView.findViewById(R.id.ib_delete_comment_item);
        }
    }
}
