package com.example.jointplanning;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.preference.PreferenceManager;

public class PreferenceUtils {

    public static void refreshTheme(Context context){
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String themeName = pref.getString("theme", "Тёмная тема");
        int themeToSet = R.style.ThemeDark;
        switch (themeName){
            case "Тёмная тема":
                themeToSet = R.style.ThemeDark;
                break;
            case "Светлая тема":
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
        Drawable unwrappedDrawableBig = AppCompatResources.getDrawable(context, R.drawable.rectangle_bigcard);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        Drawable wrappedDrawableBig = DrawableCompat.wrap(unwrappedDrawableBig);

        DrawableCompat.setTint(wrappedDrawable, color);
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.ADD);
        DrawableCompat.setTint(wrappedDrawableBig, color);
        DrawableCompat.setTintMode(wrappedDrawableBig, PorterDuff.Mode.ADD);
    }

    public static String[] getSequence(Context context){
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String sequenceName = pref.getString("sequence", context.getResources().getStringArray(R.array.sequence)[0]);
        String[] CARDS = new String[0];
        switch (sequenceName){
            case "Стандартная":
                CARDS = new String[]{"0", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "coffee"};
                break;
            case "Фибоначчи":
                CARDS = new String[]{"0", "1", "2", "3", "5", "8", "13", "21", "34", "55", "?", "coffee"};
                break;
            case "Натуральная":
                CARDS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "?", "coffee"};
                break;
            case "Часовая":
                CARDS = new String[]{"0", "1", "2", "4", "6", "8", "16", "24", "32", "40", "?", "coffee"};
                break;
            default:
                CARDS = new String[]{"0", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "coffee"};
                break;
        }
        return CARDS;
    }

    public static int refreshResultTextColor(Context context){
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String themeName = pref.getString("theme", "Тёмная тема");
        int themeToSet = R.style.AppTheme;
        switch (themeName){
            case "Тёмная тема":
                themeToSet = R.style.RedText;
                break;
            case "Розовая тема":
                themeToSet = R.style.RedText;
                break;
            case "Синяя тема":
                themeToSet = R.style.BlueText;
                break;
            case "Зелёная тема":
                themeToSet = R.style.GreenText;
                break;
            default:
                break;
        }
        return themeToSet;
    }
}
