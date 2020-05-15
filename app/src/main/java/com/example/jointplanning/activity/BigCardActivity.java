package com.example.jointplanning.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.fragment.BigCardFragment;

public class BigCardActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return BigCardFragment.newInstance(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
