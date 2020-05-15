package com.example.jointplanning.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
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

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.jointplanning.ColorsUtils;
import com.example.jointplanning.R;
import com.example.jointplanning.activity.InfoActivity;
import com.example.jointplanning.activity.RequestsActivity;
import com.example.jointplanning.activity.SettingsActivity;

public class OfflineFragment extends Fragment {
    private final String[] CARDS = {"0", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "coffee"};
    private GridView mGridView;
    private ArrayAdapter<String> mAdapter;
    private final String TAG = "OfflineFragment";

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        OfflineFragment fragment = new OfflineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ColorsUtils.refreshShirts(getActivity());
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
                Toast.makeText(getContext(), "GridView item clicked : " +selectedItem + "\nAt index position : " + position, Toast.LENGTH_SHORT).show();
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
