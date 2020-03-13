package com.example.jointplanning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.R;
import com.example.jointplanning.TaskJsonLab;
import com.example.jointplanning.model.Task;

import java.io.IOException;


public class TaskFragment extends Fragment {
    private static final String ARG_TASK_ID = "task_id";

    private TextView mTextTask;
    private TextView mEstimate;
    private TextView mPriority;
    private TextView mAuthor;
    private TextView mDateCreated;
    private TextView mDateEstimated;
    private Task mTask;

    public static TaskFragment newInstance(long taskId) {
        Bundle args = new Bundle();
        args.putLong(ARG_TASK_ID, taskId);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long taskId = getArguments().getLong(ARG_TASK_ID);
        try {
            mTask = TaskJsonLab.get().getTask(taskId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mTextTask = view.findViewById(R.id.task_text);
        mTextTask.setText(mTask.getC_description());

        mEstimate = view.findViewById(R.id.task_estimate);
        mEstimate.setText(String.valueOf(mTask.getN_size()));

        mPriority = view.findViewById(R.id.task_priority);
        mPriority.setText(String.valueOf(mTask.getN_priority()));

        mAuthor = view.findViewById(R.id.task_author);
        mAuthor.setText(mTask.getC_author());

        mDateCreated = view.findViewById(R.id.task_created_date);
        mDateCreated.setText(String.valueOf(mTask.getDx_created()));

        mDateEstimated = view.findViewById(R.id.task_estimated_date);
        mDateEstimated.setText(String.valueOf(mTask.getD_done_date()));
        return view;
    }
}
