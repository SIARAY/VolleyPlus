package ir.siaray.volleyplus.util;

import java.util.Arrays;

/**
 * Created by SIARAY on 10/30/2017.
 */

public class Log {
    private static final String TAG = "VolleyPlus";

    public static <T> void i(T param) {
        android.util.Log.i(TAG, String.valueOf(param));
    }
}
