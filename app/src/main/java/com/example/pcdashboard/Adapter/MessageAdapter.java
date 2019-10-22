package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final int LOCAL = 1;
    final int REMOTE = 0;
    private String selfId;
    private Context context;
    private ArrayList<ChatMessage> messages;
    private OnItemClickListener listener;

    public MessageAdapter(Context context, ArrayList<ChatMessage> messages) {
        this.context = context;
        this.messages = messages;
        selfId = SharedPreferencesUtils.loadSelf(context).getId();
    }

    public interface OnItemClickListener {
        void onClick();
    }

    public void update(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getUserId().equals(selfId))
            return LOCAL;
        else return REMOTE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        if (viewType == LOCAL) {
            view = inflater.inflate(R.layout.item_local_message, parent, false);
            return new LocalHolder(view);
        } else {
            view = inflater.inflate(R.layout.item_remote_message, parent, false);
            return new RemoteHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message=messages.get(position);
        if(message.getUserId().equals(selfId)){
            ((LocalHolder)holder).tvContent.setText(message.getContent());
            ((LocalHolder)holder).tvTime.setText(message.getTime());
        }else {
            Glide.with(context).load(message.getUserAvatar()).override(32,32).centerCrop().into(((RemoteHolder)holder).ivAvatar);
            ((RemoteHolder)holder).tvName.setText(message.getUserName());
            ((RemoteHolder)holder).tvContent.setText(message.getContent());
            ((RemoteHolder)holder).tvTime.setText(message.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class LocalHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        private TextView tvTime;

        public LocalHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content_local_msg);
            tvTime = itemView.findViewById(R.id.tv_time_local_msg);
        }
    }

    class RemoteHolder extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvContent;
        private TextView tvTime;

        public RemoteHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar_remote_msg);
            tvName = itemView.findViewById(R.id.tv_name_remote_msg);
            tvContent = itemView.findViewById(R.id.tv_content_remote_msg);
            tvTime = itemView.findViewById(R.id.tv_time_remote_msg);
        }
    }
}
