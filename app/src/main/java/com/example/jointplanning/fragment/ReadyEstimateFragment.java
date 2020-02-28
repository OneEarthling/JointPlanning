package com.example.jointplanning.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.R;

public class ReadyEstimateFragment extends Fragment {
    private final String[] CARDS = {"0", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "coffee"};
    private GridView mGridView;
    private ArrayAdapter<String> mAdapter;
    private TextView mUserEstimate;

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
                Toast.makeText(getContext(), "GridView item clicked : " +selectedItem + "\nAt index position : " + position, Toast.LENGTH_SHORT).show();
                mUserEstimate.setText(selectedItem);
            }
        });
        return view;
    }

}
