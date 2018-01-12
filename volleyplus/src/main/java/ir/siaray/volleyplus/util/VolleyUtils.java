package ir.siaray.volleyplus.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ir.siaray.volleyplus.VolleyPlus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Siamak on 11/08/2017.
 */

public class VolleyUtils {

    public static String buildGetRequestUrl(String url, Map<String, String> params) {
        if (!TextUtils.isEmpty(url)
                && params != null
                && params.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value != null) {
                    try {
                        value = URLEncoder.encode(String.valueOf(value), "UTF-8");

                        if (builder.length() > 0)
                            builder.append("&");
                        builder.append(key).append("=").append(value);
                    } catch (UnsupportedEncodingException e) {
                    }
                }
            }
            url += "?" + builder.toString();
        }
        return url;
    }

    public static String buildGetRequestUrl(String url, JSONObject params) {
        if (!TextUtils.isEmpty(url)
                && params != null
                && params.keys() != null) {
            StringBuilder builder = new StringBuilder();
            for (Iterator<String> it = params.keys(); it.hasNext(); ) {
                String key = it.next();
                Object value = null;
                try {
                    value = params.get(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    try {
                        value = URLEncoder.encode(String.valueOf(value), "UTF-8");

                        if (builder.length() > 0)
                            builder.append("&");
                        builder.append(key).append("=").append(value);
                    } catch (UnsupportedEncodingException e) {
                    }
                }
            }
            url += "?" + builder.toString();
        }
        return url;
    }

    public static String buildGetRequestUrl(String url, JSONArray params) {
        if (!TextUtils.isEmpty(url)
                && params != null
                && params.length() > 0) {
            StringBuilder builder = new StringBuilder();
            for (int index = 0; index < params.length(); index++) {
                Object value = null;
                try {
                    value = params.get(index);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    try {
                        value = URLEncoder.encode(String.valueOf(value), "UTF-8");

                        if (builder.length() > 0)
                            builder.append("&");
                        builder.append(index).append("=").append(value);
                    } catch (UnsupportedEncodingException e) {
                    }
                }
            }
            url += "?" + builder.toString();
        }
        return url;
    }
}
