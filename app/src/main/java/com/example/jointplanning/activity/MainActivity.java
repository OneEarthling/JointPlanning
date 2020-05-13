package com.example.jointplanning.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.jointplanning.R;
import com.example.jointplanning.activity.SingleFragmentActivity;
import com.example.jointplanning.authorization.Authorization;
import com.example.jointplanning.fragment.AuthorizationFragment;
import com.example.jointplanning.fragment.OfflineFragment;
import com.example.jointplanning.fragment.TaskListFragment;

public class MainActivity extends SingleFragmentActivity {
    public MainActivity() {
        super(true);
    }

    @Override
    protected Fragment createFragment() {
        return OfflineFragment.newInstance();
    }

}
