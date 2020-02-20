package com.example.jointplanning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.R;

public class SettingsFragment extends Fragment {
    private EditText mNickname;
    private Spinner mTheme;
    private Spinner mShirtCard;

    String[] themes = {"Red", "Green", "Blue"};
    String[] shirts = {"Temporary", "Example", "of", "Shirts"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mNickname = view.findViewById(R.id.nickname_et);
        // shared preference here


        mTheme = view.findViewById(R.id.spinner_theme);
        ArrayAdapter<String> adapterThemes = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, themes);
        adapterThemes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTheme.setAdapter(adapterThemes);

        mShirtCard = view.findViewById(R.id.spinner_shirtcard);
        ArrayAdapter<String> adapterShirtCards = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, shirts);
        adapterShirtCards.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mShirtCard.setAdapter(adapterShirtCards);

        return view;
    }
}
