package ir.siaray.volleyplus.util;

import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import ir.siaray.volleyplus.VolleyPlus;

import java.io.UnsupportedEncodingException;

/**
 * Created by Siamak on 11/08/2017.
 */

public class VolleyUtils {
/*    public static String getCache(String url) {
        //دریافت دیتای کش شده برای url خاص
        Cache cache = VolleyPlus.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(url);
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
                return data;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // Cached response doesn't exists. Make network call here
        }
        return null;
    }

    public static void invalidateVolleyCache(String url) {
        //بی اعتبار کردن (باطل کردن) کش برای url خاص (کش حذف نمی شود)
        VolleyPlus.getInstance().getRequestQueue().getCache().invalidate(url, true);
    }

    public static void disableCache(Request request) {
        //خاموش کردن(غیرفعال کردن) کش برای درخواست خاص
        request.setShouldCache(false);
    }

    public static void clearCache(String url) {
        //حذف کش(Cache) مربوط به یک دامنه خاص
        VolleyPlus.getInstance().getRequestQueue().getCache().remove(url);
    }

    public static void clearCache() {
        //حذف تمامی کش(Cache)ها
        VolleyPlus.getInstance().getRequestQueue().getCache().clear();
    }

    public static boolean cancelRequest(String request) {
        if (request != null) {
            //Request canceled
            //VolleyPlus.getInstance().getRequestQueue().cancelAll(request);
            VolleyPlus.getInstance().cancelPendingRequests(request);
            return true;
        } else {
            //Request is null (The request can not be canceled)
            return false;
        }

    }

    *//**
     * Constructs a new retry policy.
     *
     * @param initialTimeoutMs  The initial timeout for the policy.
     * @param maxNumRetries     The maximum number of retries.
     * @param backoffMultiplier Backoff multiplier for the policy.
     *//*
    public static void setTimeoutRequest(Request request
            , int initialTimeoutMs
            , int maxNumRetries
            , float backoffMultiplier) {
        if (initialTimeoutMs <= 0)
            initialTimeoutMs = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        if (maxNumRetries <= 0)
            maxNumRetries = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
        if (backoffMultiplier <= 0f)
            backoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        request.setRetryPolicy(new DefaultRetryPolicy(initialTimeoutMs
                , maxNumRetries
                , backoffMultiplier));
    }*/
}
