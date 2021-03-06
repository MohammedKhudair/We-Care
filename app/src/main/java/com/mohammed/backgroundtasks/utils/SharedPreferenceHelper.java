package com.mohammed.backgroundtasks.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;


public class SharedPreferenceHelper {
    private static final String PREF_TIMES_NOTIFIED_TODAY = "TimesNotifiedToday";
    private static final String SCREEN_STATE = "SCREEN_STATE";


    //=======================================================================
    // Save times notified today
    //======================================================================

    public static void saveTimesNotifiedToday(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        int oldTimesNotified = SharedPreferenceHelper.getTimesNotifiedToday(context);
        sp.edit().putInt(PREF_TIMES_NOTIFIED_TODAY, oldTimesNotified + 1).apply();
    }

    public static int getTimesNotifiedToday(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(PREF_TIMES_NOTIFIED_TODAY, 0);
    }

    public static void reinitializeTimesNotifiedToday(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(PREF_TIMES_NOTIFIED_TODAY).apply();
    }

    //=======================================================================
    // Save screen state
    //======================================================================

    public static void screenState(Context context,boolean screenState) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(SCREEN_STATE,screenState).apply();
    }
    public static boolean getScreenState(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(SCREEN_STATE,true);
    }


}
