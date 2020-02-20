package com.example.jointplanning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jointplanning.R;

public class SimilarTaskFragment extends Fragment {
    private RecyclerView mSimilarTasksRecyclerView;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        SimilarTaskFragment fragment = new SimilarTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joint_planning, container, false);
        mSimilarTasksRecyclerView = view.findViewById(R.id.tasks_recycler_view);
        mSimilarTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

}
