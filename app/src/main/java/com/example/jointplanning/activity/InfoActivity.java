package com.example.jointplanning.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jointplanning.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(this);
        String themeName = pref.getString("theme", "Theme1");
        Log.d("one", "" + themeName);
        switch (themeName) {
            case "Тёмная тема":
                Log.d("one", "theme1");
                setTheme(R.style.AppTheme);
                break;
            case "Розовая тема":
                Log.d("one", "theme2");
                setTheme(R.style.ThemePink);
                break;
            case "Синяя тема":
                Log.d("one", "theme3");
                setTheme(R.style.ThemeBlue);
                break;
            case "Зелёная тема":
                Log.d("one", "theme4");
                setTheme(R.style.ThemeGreen);
                break;
            default:
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(0, 0);
    }
}
