package com.example.jointplanning.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.jointplanning.R;
import com.example.jointplanning.activity.MainActivity;
import com.example.jointplanning.activity.RequestsActivity;

public class ReadyTaskFragment extends Fragment {

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        ReadyTaskFragment fragment = new ReadyTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ready_task, container, false);

        Button mReady = view.findViewById(R.id.im_ready);
        mReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, ReadyEstimateFragment.newInstance());
                transaction.commit();
            }
        });

        Button mSimilarTasks = view.findViewById(R.id.similar_tasks);
        mSimilarTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // открытие похожих задач
                // изменить!
                Intent intent = new Intent(getActivity(), RequestsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
