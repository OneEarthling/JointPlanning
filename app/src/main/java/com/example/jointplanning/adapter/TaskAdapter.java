package com.example.jointplanning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jointplanning.R;
import com.example.jointplanning.adapter.holder.TaskHolder;
import com.example.jointplanning.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
    private Context mContext;
    private List<Task> mTasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        mContext = context;
        mTasks = tasks;
//        TaskLab taskLab = TaskLab.get();
//        mTasks = taskLab.getTasks();
        //new getTasksDB().execute();
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.list_item_wpriority, parent, false);

        return new TaskHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }
}
