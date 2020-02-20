package com.example.jointplanning.activity;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.activity.SingleFragmentActivity;
import com.example.jointplanning.fragment.TaskListFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }
}
