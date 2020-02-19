package com.example.jointplanning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListFragment extends Fragment {
private RecyclerView mTasksRecyclerView;
private TaskAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joint_planning, container, false);
        mTasksRecyclerView = view.findViewById(R.id.tasks_recycler_view);
        mTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));;


        updateUI();
        return view;
    }

    private void updateUI() {
        TaskLab taskLab = TaskLab.get(getActivity());
        List <Task> tasks = taskLab.getTasks();
        mAdapter = new TaskAdapter(tasks);
        mTasksRecyclerView.setAdapter(mAdapter);

    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextTask;
        private TextView mEstimate;
        private TextView mPriority;
        private Task mTask;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
               super(inflater.inflate(R.layout.list_item_wpriority, parent, false));
               itemView.setOnClickListener(this);
               mTextTask = itemView.findViewById(R.id.task_text);
               mEstimate = itemView.findViewById(R.id.task_estimate);
               mPriority = itemView.findViewById(R.id.task_priority);
        }

        public void bind(Task task){
            mTask = task;
            mTextTask.setText(task.getTextTask());
            mEstimate.setText(String.valueOf(task.getEstimate()));
            mPriority.setText(String.valueOf(task.getPriority()));

        }

        @Override
        public void onClick(View v) {
            Intent intent = TaskActivity.newIntent(getActivity(), mTask.getId());
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
        private List <Task> mTasks;

        public TaskAdapter(List <Task> tasks){
            mTasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_joint_planning, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.ready:
                item.setChecked(true);
                intent = ReadyActivity.newIntent(getActivity());
                startActivity(intent);
                return true;
            case R.id.settings:
                intent = SettingsActivity.newIntent(getActivity());
                startActivity(intent);
                return true;
            case R.id.exit:
                getActivity().finish();
                System.exit(0);
                return true;
            case R.id.info:
                intent = new Intent(getContext(), InfoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
