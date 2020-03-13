package com.example.jointplanning.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.fragment.TaskFragment;

public class TaskActivity extends SingleFragmentActivity {
    public static  final String EXTRA_TASK_ID = "com.example.jointplanning.task_id";

    public static Intent newIntent(Context packageContext, long taskId){
        Intent intent = new Intent(packageContext, TaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        long taskId = getIntent().getLongExtra(EXTRA_TASK_ID, 0);
        return TaskFragment.newInstance(taskId);
    }
}
