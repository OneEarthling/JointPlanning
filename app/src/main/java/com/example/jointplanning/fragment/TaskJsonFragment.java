package com.example.jointplanning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.R;
import com.example.jointplanning.TaskJsonLab;
import com.example.jointplanning.TaskLab;
import com.example.jointplanning.model.Task;
import com.example.jointplanning.model.TaskJson;

import java.io.IOException;


public class TaskJsonFragment extends Fragment {
    private static final String ARG_TASK_ID = "task_id";

    private TextView mTextTask;
    private TextView mEstimate;
    private TextView mPriority;
    private TextView mAuthor;
    private TextView mDateCreated;
    private TextView mDateEstimated;
    private TaskJson mTask;

    public static TaskJsonFragment newInstance(long taskId) {
        Bundle args = new Bundle();
        args.putLong(ARG_TASK_ID, taskId);

        TaskJsonFragment fragment = new TaskJsonFragment();
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
        mTextTask.setText(mTask.getCDescription());

        mEstimate = view.findViewById(R.id.task_estimate);
        mEstimate.setText(String.valueOf(mTask.getNSize()));

        mPriority = view.findViewById(R.id.task_priority);
        mPriority.setText(String.valueOf(mTask.getNPriority()));

        mAuthor = view.findViewById(R.id.task_author);
        mAuthor.setText(mTask.getCAuthor());

        mDateCreated = view.findViewById(R.id.task_created_date);
        mDateCreated.setText(String.valueOf(mTask.getDxCreated()));

        mDateEstimated = view.findViewById(R.id.task_estimated_date);
        mDateEstimated.setText(String.valueOf(mTask.getDDoneDate()));
        return view;
    }
}
