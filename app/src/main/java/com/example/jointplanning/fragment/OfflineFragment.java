package com.example.jointplanning.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.PreferenceUtilManager;
import com.example.jointplanning.R;
import com.example.jointplanning.activity.BigCardActivity;
import com.example.jointplanning.activity.InfoActivity;
import com.example.jointplanning.activity.RequestsActivity;
import com.example.jointplanning.activity.SettingsActivity;

public class OfflineFragment extends Fragment {
    private String[] CARDS = new String[0];
    private GridView mGridView;
    private ArrayAdapter<String> mAdapter;
    private final String TAG = "OfflineFragment";

    public static Fragment newInstance() {
//        Bundle args = new Bundle();
//        OfflineFragment fragment = new OfflineFragment();
//        fragment.setArguments(args);
        return new OfflineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        PreferenceUtilManager.refreshShirts(getActivity());
        CARDS = PreferenceUtilManager.getSequence(getActivity());
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offline, container, false);
        mAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_gridview, CARDS);

        mGridView = view.findViewById(R.id.grid_view);
        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getContext(), "GridView item clicked : " +selectedItem + "\nAt index position : " + position, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("estimate", selectedItem);
                Intent intent = new Intent(getContext(), BigCardActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_offline, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.offline_settings:
                Intent intent = SettingsActivity.newIntent(getActivity());
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                return true;
            case R.id.sign_in:
                intent = new Intent(getContext(), RequestsActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                return true;
            case R.id.info:
                intent = new Intent(getContext(), InfoActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
