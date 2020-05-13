package com.example.jointplanning.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.R;
import com.example.jointplanning.fragment.SettingsFragment;

public class SettingsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, SettingsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //If you want to insert data in your settings
        /*SettingsFragment settingsFragment = new SettingsFragment();
        //settingsFragment.
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, settingsFragment).commit();*/

        //Else
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SettingsFragment()).commit();
    }


    @Override
    protected Fragment createFragment() {
        return SettingsFragment.newInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(0, 0);
    }
}
