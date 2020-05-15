package com.example.jointplanning;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.preference.PreferenceManager;

public class ColorsUtils {

    public static void refreshTheme(Context context){
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String themeName = pref.getString("theme", "Тёмная тема");
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
        context.setTheme(themeToSet);
    }

    public static void refreshShirts(Context context){
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String shirtName = pref.getString("shirts", context.getResources().getStringArray(R.array.shirts)[0]);
        int color = Color.RED;
        switch (shirtName){
            case "Красная рубашка":
                color = context.getResources().getColor(R.color.dark_red);
                break;
            case "Синяя рубашка":
                color = context.getResources().getColor(R.color.dark_blue);
                break;
            case "Зелёная рубашка":
                color = context.getResources().getColor(R.color.dark_green);
                break;
            case "Чёрная рубашка":
                color = Color.BLACK;
                break;
            default:
                break;
        }
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.rectangle_card);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);

        DrawableCompat.setTint(wrappedDrawable, color);
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.ADD);
    }
}
