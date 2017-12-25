package ir.siaray.volleyplussample.util;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by SIARAY on 06/08/2017.
 */

public class Constants {
    public static String jsonObjectUrl = "https://api.androidhive.info/volley/person_object.json";
    public static String jsonArrayUrl = "https://api.androidhive.info/volley/person_array.json";
    public static String jsonStringUrl = "https://api.androidhive.info/volley/string_response.html";

    //public static String jsonObjectUrl = "http://192.168.1.200/note-server/JsonObjectTest.php";
    //public static String jsonArrayUrl = "http://192.168.1.200/note-server/JsonArrayTest.php";
    //public static String jsonStringUrl = "http://192.168.1.200/note-server/JsonStringTest.php";

    public static final int REQUEST_TIME_OUT = 5000;
    public static final int REQUEST_NUM_OF_RETRY = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
    public static final float REQUEST_BACK_OFF_MULTIPLAIER = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
}
