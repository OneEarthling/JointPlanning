package com.example.jointplanning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.R;

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
        return view;
    }

}
