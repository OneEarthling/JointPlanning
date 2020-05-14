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
        int themeToSet = R.style.AppTheme;
        switch (themeName){
            case "Тёмная тема":
                themeToSet = R.style.AppTheme;
                break;
            case "Розовая тема":
                themeToSet = R.style.ThemePink;
                break;
            case "Синяя тема":
                themeToSet = R.style.ThemeBlue;
                break;
            case "Зелёная тема":
                themeToSet = R.style.ThemeGreen;
                break;
            default:
                break;
        }
        setTheme(themeToSet);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(0, 0);
    }
}
