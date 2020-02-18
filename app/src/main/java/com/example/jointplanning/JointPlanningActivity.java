package com.example.jointplanning;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class JointPlanningActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new JointPlanningFragment();
    }

}
