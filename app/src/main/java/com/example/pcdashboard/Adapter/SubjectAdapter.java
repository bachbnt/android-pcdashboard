package com.example.pcdashboard.Adapter;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Manager.CustomToast;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.Subject;
import com.example.pcdashboard.R;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Subject> subjects;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEdit(Subject subject,int position, String name,String time,String teacher);
        void onDelete(Subject subject);
    }

    public SubjectAdapter(Context context, ArrayList<Subject> subjects,OnItemClickListener listener) {
        this.context = context;
        this.subjects = subjects;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Subject subject = subjects.get(position);
        holder.etName.setText(subject.getName());
        holder.etTime.setText(subject.getTime());
        holder.etTeacher.setText(subject.getTeacherOrClass());
        holder.etName.setInputType(InputType.TYPE_NULL);
        holder.etTime.setInputType(InputType.TYPE_NULL);
        holder.etTeacher.setInputType(InputType.TYPE_NULL);
        if(SharedPreferencesUtils.loadSelf(context).getRole().equals("ROLE_TEACHER"))
            holder.etTeacher.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_class_hot_24dp, 0, 0, 0);
        holder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ibEdit.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                if (holder.etName.getInputType()==InputType.TYPE_NULL) {
                    holder.etName.setInputType(InputType.TYPE_CLASS_TEXT);
                    holder.etTime.setInputType(InputType.TYPE_CLASS_TEXT);
                    holder.etTeacher.setInputType(InputType.TYPE_CLASS_TEXT);
                    holder.etName.setFocusable(true);
                    holder.etName.requestFocus();
                    holder.ibEdit.setImageResource(R.drawable.ic_success_24dp);
                    CustomToast.makeText(context, "Chế độ chỉnh sửa", CustomToast.LENGTH_SHORT, CustomToast.WARNING).show();
                } else {
                    holder.etName.setInputType(InputType.TYPE_NULL);
                    holder.etTime.setInputType(InputType.TYPE_NULL);
                    holder.etTeacher.setInputType(InputType.TYPE_NULL);
                    holder.ibEdit.setImageResource(R.drawable.ic_edit_white_24dp);
                    listener.onEdit(subject,position,holder.etName.getText().toString(),holder.etTime.getText().toString(),holder.etTeacher.getText().toString());
                    CustomToast.makeText(context, "Xác nhận", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS).show();
                }
            }
        });
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ibDelete.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                listener.onDelete(subject);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private EditText etName;
        private EditText etTime;
        private EditText etTeacher;
        private ImageButton ibDelete;
        private ImageButton ibEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etName = itemView.findViewById(R.id.et_name_subject_item);
            etTime = itemView.findViewById(R.id.et_time_subject_item);
            etTeacher = itemView.findViewById(R.id.et_teacher_subject_item);
            ibDelete = itemView.findViewById(R.id.ib_delete_subject_item);
            ibEdit = itemView.findViewById(R.id.ib_edit_subject_item);
        }
    }
}
