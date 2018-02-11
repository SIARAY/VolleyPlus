package ir.siaray.volleyplussample.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by SIAM on 9/3/2017.
 */

public class Utils {

    private static String MY_PREFS_NAME = "VolleySampleSP";

    public static void showProgressBar(ProgressBar progressbar) {
        if (progressbar.getVisibility() != View.VISIBLE) {
            progressbar.setVisibility(View.VISIBLE);
        }
    }

    public static void hideProgressBar(ProgressBar progressbar) {
        if (progressbar.getVisibility() != View.INVISIBLE) {
            progressbar.setVisibility(View.INVISIBLE);
        }
    }

    public static void saveInSP(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void saveInSP(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String getFromSP(Context context, String key, String defValue) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, defValue);
    }

    public static int getFromSP(Context context, String key, int defValue) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

}
