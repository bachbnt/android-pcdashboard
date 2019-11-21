package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Model.User;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private ArrayList<User> users;
    private OnItemCLickListener listener;

    public UserAdapter(Context context, ArrayList<User> users, OnItemCLickListener listener) {
        this.context = context;
        this.users = users;
        this.listener = listener;
    }

    public interface OnItemCLickListener{
        void onClick(User user);
    }
    public void update(ArrayList<User> users){
        this.users=users;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user=users.get(position);
        Glide.with(context).load(user.getAvatar()).centerCrop().override(50,50).into(holder.ivAvatar);
        holder.tvName.setText(user.getName());
        holder.rlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rlLayout.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                listener.onClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    private ImageView ivAvatar;
    private TextView tvName;
    private RelativeLayout rlLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar=itemView.findViewById(R.id.iv_avatar_user_item);
            tvName=itemView.findViewById(R.id.tv_name_user_item);
            rlLayout=itemView.findViewById(R.id.rl_layout_user_item);
        }
    }
}
