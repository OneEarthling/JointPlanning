package com.example.jointplanning;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.activity.SingleFragmentActivity;


public class SettingsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, SettingsActivity.class);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        return SettingsFragment.newInstance();
    }
}
