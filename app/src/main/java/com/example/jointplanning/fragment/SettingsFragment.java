package com.example.jointplanning.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.widget.Toast;

import com.example.jointplanning.ColorsUtils;
import com.example.jointplanning.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final int RESULT_CODE_THEME_UPDATED = 1;
    public static final String  TAG = "SettingsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, final String rootKey) {
        Log.d(TAG, "onCreatePreferences");
        setPreferencesFromResource(R.xml.preferences, rootKey);
        //findPreference("theme").setOnPreferenceChangeListener(new RefershActivityOnPreferenceChangeListener(RESULT_CODE_THEME_UPDATED));
        findPreference("theme").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.d(TAG, "onPreferenceChange");
                ColorsUtils.refreshTheme(getActivity());
                Toast.makeText(getActivity(), newValue + " была выбрана", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class RefershActivityOnPreferenceChangeListener implements androidx.preference.Preference.OnPreferenceChangeListener {
        private final int resultCode;
        public RefershActivityOnPreferenceChangeListener(int resultCode) {
            this.resultCode = resultCode;
        }

        @Override
        public boolean onPreferenceChange(androidx.preference.Preference preference, Object newValue) {
            getActivity().setResult(resultCode);
            SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(getActivity());
            String themeName = pref.getString("theme", "Theme1");
            Log.d("one", "" + themeName);
            switch (themeName){
                case "Тёмная тема":
                    Log.d("onee", "theme1");
                    getActivity().setTheme(R.style.AppTheme);
                    break;
                case "Розовая тема":
                    Log.d("onee", "theme2");
                    getActivity().setTheme(R.style.ThemePink);
                    break;
                case "Синяя тема":
                    Log.d("onee", "theme3");
                    getActivity().setTheme(R.style.ThemeBlue);
                    break;
                case "Зелёная тема":
                    Log.d("onee", "theme4");
                    getActivity().setTheme(R.style.ThemeGreen);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

}
