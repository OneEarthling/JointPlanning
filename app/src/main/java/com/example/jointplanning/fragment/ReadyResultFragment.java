package com.example.jointplanning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.PreferenceUtils;
import com.example.jointplanning.R;

public class ReadyResultFragment extends Fragment {
    public static Fragment newInstance() {
        Bundle args = new Bundle();
        ReadyResultFragment fragment = new ReadyResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ready_result, container, false);
        TextView textViewTitle = view.findViewById(R.id.result);
        int style = PreferenceUtils.refreshResultTextColor(getActivity());
        textViewTitle.setTextAppearance(getActivity(), style);
        return view;
    }
}
