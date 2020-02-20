package com.example.jointplanning.adapter.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jointplanning.R;
import com.example.jointplanning.model.Task;
import com.example.jointplanning.TaskActivity;

public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Context mContext;

    private TextView mTextTask;
    private TextView mEstimate;
    private TextView mPriority;

    private Task mTask;

    public TaskHolder(Context context, @NonNull View itemView) {
        super(itemView);
        mContext = context;

        mTextTask = itemView.findViewById(R.id.task_text);
        mEstimate = itemView.findViewById(R.id.task_estimate);
        mPriority = itemView.findViewById(R.id.task_priority);

        itemView.setOnClickListener(this);
    }

    public void bind(Task task) {
        mTask = task;
        mTextTask.setText(task.getText());
        mEstimate.setText(String.valueOf(task.getEstimate()));
        mPriority.setText(String.valueOf(task.getPriority()));
    }

    @Override
    public void onClick(View v) {
        Intent intent = TaskActivity.newIntent(mContext, mTask.getId());
        mContext.startActivity(intent);
    }
}
