package com.example.jointplanning.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.jointplanning.fragment.ReadyEstimateFragment;
import com.example.jointplanning.fragment.ReadyTaskFragment;
import com.example.jointplanning.fragment.ReadyWaitFragment;

public class ReadyActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, ReadyActivity.class);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return ReadyWaitFragment.newInstance();
//        return ReadyTaskFragment.newInstance();
//        return ReadyEstimateFragment.newInstance();
//        return ReadyResultFragment.newInstance();
    }
}
