package ir.siaray.volleyplus.util;

import java.lang.reflect.Field;

/**
 * Created by SIARAY on 10/30/2017.
 */

public class Log {
    private static final String TAG = "VolleyPlus";

    public static <T> void print(T param) {
        android.util.Log.i(TAG, String.valueOf(param));
    }

    public static String printObject(Object obj) {
        Log.print("*** " + obj.getClass().getSimpleName() + " Values ***");
        String result = "";
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Log.print(name + ": " + value);
            result = result + name + ": " + value + "\n";
        }
        if (result.isEmpty())
            return null;
        else
            return result;
    }
}
