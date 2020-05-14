package com.example.jointplanning.activity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import androidx.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.jointplanning.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    private boolean doubleBackToExitPressedOnce = false;
    private boolean mIsBackToExist;

    public SingleFragmentActivity() {
        super();
        mIsBackToExist = false;
    }

    public SingleFragmentActivity(boolean isBackToExist) {
        super();
        mIsBackToExist = isBackToExist;
    }

    protected abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.activity_fragment;
    }

    protected void refreshTheme(){
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        refreshTheme();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onBackPressed() {
        if(isBackToExist()) {
            if (doubleBackToExitPressedOnce) {
                finishAffinity();
                finish();
                super.onBackPressed();
                return;
            }
            doubleBackToExitPressedOnce = true;

            Toast.makeText(this, getString(R.string.signOutMessage), Toast.LENGTH_LONG).show();

            int TOAST_DURATION = 2750;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, TOAST_DURATION);
        } else {
            super.onBackPressed();
        }
    }

    private boolean isBackToExist() {
        return mIsBackToExist;
    }
}
