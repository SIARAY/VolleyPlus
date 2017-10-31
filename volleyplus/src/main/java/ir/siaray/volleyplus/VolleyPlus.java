package ir.siaray.volleyplus;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import ir.siaray.volleyplus.util.Log;
import ir.siaray.volleyplus.util.LruBitmapCache;

import java.io.UnsupportedEncodingException;

import ir.siaray.volleyplus.util.LruBitmapCache;

/**
 * Created by SIARAY on 9/15/2017.
 */

public class VolleyPlus {
    public static final String TAG = VolleyPlus.class.getSimpleName();
    private static VolleyPlus mInstance;
    private final Context mContext;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleyPlus(Context context) {
        mContext = context;
        mInstance = this;
    }

    public static VolleyPlus initialize(Context context) {
        Log.i("initialize");
        return new VolleyPlus(context);
    }

    public static synchronized VolleyPlus getInstance() {
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public boolean cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
            return true;
        }
        return false;
    }

    public static String getCache(String url) {
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

    /**
     * بی اعتبار کردن (باطل کردن) کش برای url خاص (کش حذف نمی شود)
     *
     * @param url
     */
    public static void invalidateVolleyCache(String url) {
        VolleyPlus.getInstance().getRequestQueue().getCache().invalidate(url, true);
    }

    /**
     * خاموش کردن(غیرفعال کردن) کش برای درخواست خاص
     *
     * @param request
     */
    public static void disableCache(Request request) {
        request.setShouldCache(false);
    }

    /**
     * حذف کش(Cache) مربوط به یک دامنه خاص
     *
     * @param url
     */
    public static void clearCache(String url) {
        VolleyPlus.getInstance().getRequestQueue().getCache().remove(url);
    }

    /**
     * حذف تمامی کش(Cache)ها
     */
    public static void clearCache() {
        VolleyPlus.getInstance().getRequestQueue().getCache().clear();
    }

    public static boolean cancelRequest(String request) {
        if (request != null) {
            //VolleyPlus.getInstance().getRequestQueue().cancelAll(request);
            if (getInstance().cancelPendingRequests(request))
                return true;
        }
        //Request is null (The request can not be canceled)
        return false;
    }

    /**
     * Constructs a new retry policy.
     *
     * @param initialTimeoutMs  The initial timeout for the policy.
     * @param maxNumRetries     The maximum number of retries.
     * @param backoffMultiplier Backoff multiplier for the policy.
     */
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
    }
}
