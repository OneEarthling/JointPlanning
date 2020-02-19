package com.example.jointplanning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class TaskFragment extends Fragment {
    private static final String ARG_TASK_ID = "task_id";

    private TextView mTextTask;
    private TextView mEstimate;
    private TextView mPriority;
    private Task mTask;

    public static TaskFragment newInstance(UUID taskId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskLab.get(getActivity()).getTask(taskId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mTextTask = view.findViewById(R.id.task_text);
        mTextTask.setText(mTask.getTextTask());

        mEstimate = view.findViewById(R.id.task_estimate);
        mEstimate.setText(String.valueOf(mTask.getEstimate()));

        mPriority = view.findViewById(R.id.task_priority);
        mPriority.setText(String.valueOf(mTask.getPriority()));

        return view;
    }
}
