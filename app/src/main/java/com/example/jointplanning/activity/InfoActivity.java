package com.example.jointplanning.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jointplanning.PreferenceUtilManager;
import com.example.jointplanning.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferenceUtilManager.refreshTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(0, 0);
    }
}
