package com.example.jointplanning.fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jointplanning.R;

public class BigCardFragment extends Fragment {
    private static final String ARG_VALUE = "value";
    private TextView mBigCard;

    public static Fragment newInstance(String value) {
        Bundle args = new Bundle();
        args.putString(ARG_VALUE, value);
        BigCardFragment fragment = new BigCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bigcard, container, false);
        mBigCard = view.findViewById(R.id.big_card);

        if( getArguments() != null) {
            String text = getArguments().getString(ARG_VALUE);
            if (text.equals("coffee")){
                mBigCard.setTextSize(75);
            }
            mBigCard.setText(text);
        }

        return view;
    }


}
