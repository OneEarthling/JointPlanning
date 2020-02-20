package com.example.jointplanning;

import android.content.Context;
import android.content.Intent;
import java.util.UUID;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.activity.SingleFragmentActivity;

public class TaskActivity extends SingleFragmentActivity {
    public static  final String EXTRA_TASK_ID = "com.example.jointplanning.task_id";

    public static Intent newIntent(Context packageContext, long taskId){
        Intent intent = new Intent(packageContext, TaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        return TaskFragment.newInstance(taskId);
    }
}
