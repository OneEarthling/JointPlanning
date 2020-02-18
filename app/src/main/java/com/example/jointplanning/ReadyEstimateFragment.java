package com.example.jointplanning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ReadyEstimateFragment extends Fragment {
    public static Fragment newInstance() {
        Bundle args = new Bundle();
        ReadyEstimateFragment fragment = new ReadyEstimateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ready_estimate, container, false);
        return view;
    }
}
