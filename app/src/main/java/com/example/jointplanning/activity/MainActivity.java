package com.example.jointplanning.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.jointplanning.activity.SingleFragmentActivity;
import com.example.jointplanning.fragment.AuthorizationFragment;
import com.example.jointplanning.fragment.TaskListFragment;

public class MainActivity extends SingleFragmentActivity {
    private final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 100;

    public MainActivity() {
        super(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            // Permission is not granted
        }
        else {
            Toast
                    .makeText(this,
                            "Permission already granted",
                            Toast.LENGTH_SHORT)
                    .show();
        }

    }

   @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,
                        "Write Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(this,
                        "Write Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
                // выйти?
            }
        }
    }

    @Override
    protected Fragment createFragment() {
        return AuthorizationFragment.newInstance();
    }
}
