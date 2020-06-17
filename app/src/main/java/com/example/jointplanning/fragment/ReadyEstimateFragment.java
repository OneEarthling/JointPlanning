package com.example.jointplanning.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.jointplanning.PreferenceUtilManager;
import com.example.jointplanning.R;
import com.example.jointplanning.activity.BigCardActivity;

public class ReadyEstimateFragment extends Fragment {
    private String[] CARDS = new String[0];
    private GridView mGridView;
    private ArrayAdapter<String> mAdapter;
    private TextView mUserEstimate;
    private TextView mTimeLeft;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        ReadyEstimateFragment fragment = new ReadyEstimateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        CARDS = PreferenceUtilManager.getSequence(getActivity());
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ready_estimate, container, false);
        mAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_gridview, CARDS);

        mGridView = view.findViewById(R.id.grid_view);
        mUserEstimate = view.findViewById(R.id.user_estimate);

        mGridView.setAdapter(mAdapter);
        mGridView.setNumColumns(3);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = ((TextView)view.findViewById(R.id.btn_card)).getText().toString();
//                Toast.makeText(getContext(), "!!!" + position, Toast.LENGTH_SHORT).show();

                String selectedItem = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getContext(), "GridView item clicked : " +selectedItem + "\nAt index position : " + position, Toast.LENGTH_SHORT).show();
                mUserEstimate.setText(selectedItem);

                Fragment f = new ReadyResultFragment();
                Bundle bundle = new Bundle();
                bundle.putString("estimate", selectedItem);
                f.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, f);
                transaction.commit();
            }
        });

        mUserEstimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, ReadyResultFragment.newInstance());
                transaction.commit();
            }
        });

        mTimeLeft = view.findViewById(R.id.time_left);
        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                mTimeLeft.setText("время вышло!");
            }
        }.start();
        return view;
    }

}
