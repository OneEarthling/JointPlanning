package com.example.jointplanning.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.fragment.BigCardFragment;

public class BigCardActivity extends SingleFragmentActivity {
    private String mEstimate;

    @Override
    protected Fragment createFragment() {
        mEstimate = getIntent().getStringExtra("estimate");
        return BigCardFragment.newInstance(mEstimate);
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
