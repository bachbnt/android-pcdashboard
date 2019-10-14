package com.example.pcdashboard.Manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcdashboard.R;

public class CustomToast extends Toast {
    public static final int SUCCESS=1;
    public static final int FAILURE=0;
    public static final int WARNING=-1;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CustomToast(Context context) {
        super(context);
    }
    public static Toast makeText(Context context,String msg,int duration, int type){
        Toast toast=new Toast(context);
        toast.setDuration(duration);
        View view= LayoutInflater.from(context).inflate(R.layout.layout_custom_toast,null,false);
        TextView tvMsg=view.findViewById(R.id.tv_msg_toast);
        ImageView ivIcon=view.findViewById(R.id.iv_icon_toast);
        LinearLayout llLayout=view.findViewById(R.id.ll_toast_layout);
        tvMsg.setText(msg);
        tvMsg.setGravity(Gravity.CENTER);
        switch (type){
            case SUCCESS:
                ivIcon.setImageResource(R.drawable.ic_success_24dp);
                llLayout.setBackgroundResource(R.drawable.bg_toast_success);
                break;
            case FAILURE:
                ivIcon.setImageResource(R.drawable.ic_failure_24dp);
                llLayout.setBackgroundResource(R.drawable.bg_toast_failure);
                break;
            case WARNING:
                ivIcon.setImageResource(R.drawable.ic_warning_24dp);
                llLayout.setBackgroundResource(R.drawable.bg_toast_warning);
                break;
        }
        toast.setView(view);
        return toast;
    }
}
