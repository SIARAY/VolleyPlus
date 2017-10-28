package ir.siaray.volleyplussample.classes;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by Siamak on 06/08/2017.
 */

public class Constants {
    public static String jsonObjectUrl = "http://192.168.1.200/note-server/JsonObjectTest.php"/*?name=myName&email=myEmail&password=myPass145"*/;
    public static String jsonArrayUrl = "http://192.168.1.200/note-server/JsonArrayTest.php"/*?name=myName&email=myEmail&password=myPass145"*/;
    public static String jsonStringUrl = "http://192.168.1.200/note-server/JsonStringTest.php"/*?name=myName&email=myEmail&password=myPass145"*/;

    public static final int REQUEST_TIME_OUT = 5000;
    public static final int REQUEST_NUM_OF_RETRY = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
    public static final float REQUEST_BACK_OFF_MULTIPLAIER = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
}
