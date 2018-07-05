package ir.siaray.volleyplus;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import ir.siaray.volleyplus.listener.ParseVolleyErrorListener;
import ir.siaray.volleyplus.util.LruBitmapCache;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import ir.siaray.volleyplus.util.VolleyUtils;

/**
 * Created by SIARAY on 9/15/2017.
 */

public class VolleyPlus {
    private static final String TAG = VolleyPlus.class.getSimpleName();
    private static VolleyPlus mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleyPlus(Context context) {
        mContext = context.getApplicationContext();
        mInstance = this;
    }

    public static Context getContext() {
        return mContext;
    }

    public static VolleyPlus getInstance(Context context) {
        if (mInstance == null || mContext == null) {
            return new VolleyPlus(context);
        }
        return mInstance;
    }

    public static VolleyPlus getInstance() {
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

    public static String getCache(Context context, String url) {
        //دریافت دیتای کش شده برای url خاص

        Cache cache = VolleyPlus.getInstance(context).getRequestQueue().getCache();
        Cache.Entry entry = cache.get(url);
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");
                data = VolleyUtils.unescapeString(data);
                // handle data, like converting it to xml, json, bitmap etc.
                return data;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // Cached response doesn't exists. Make network call here
        }
        return null;
    }

    public static String getCacheForGetRequest(Context context
            , String url
            , Map<String, String> params) {
        return getCache(context, VolleyUtils.buildGetRequestUrl(url, params));
    }

    public static String getCacheForGetRequest(Context context
            , String url
            , JSONObject params) {
        return getCache(context, VolleyUtils.buildGetRequestUrl(url, params));
    }

    public static String getCacheForGetRequest(Context context
            , String url
            , JSONArray params) {
        return getCache(context, VolleyUtils.buildGetRequestUrl(url, params));
    }

    /**
     * بی اعتبار کردن (باطل کردن) کش برای url خاص (کش حذف نمی شود)
     *
     * @param url
     */
    public static void invalidateCache(Context context, String url) {
        VolleyPlus.getInstance(context).getRequestQueue().getCache().invalidate(url, true);
    }

    public static void invalidateCacheForGetRequest(Context context
            , String url
            , Map<String, String> params) {
        VolleyPlus.getInstance(context)
                .getRequestQueue()
                .getCache()
                .invalidate(VolleyUtils.buildGetRequestUrl(url, params), true);
    }

    public static void invalidateCacheForGetRequest(Context context
            , String url
            , JSONObject params) {
        VolleyPlus.getInstance(context)
                .getRequestQueue()
                .getCache()
                .invalidate(VolleyUtils.buildGetRequestUrl(url, params), true);
    }

    public static void invalidateCacheForGetRequest(Context context
            , String url
            , JSONArray params) {
        VolleyPlus.getInstance(context)
                .getRequestQueue()
                .getCache()
                .invalidate(VolleyUtils.buildGetRequestUrl(url, params), true);
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
    public static void clearCache(Context context, String url) {
        VolleyPlus.getInstance(context).getRequestQueue().getCache().remove(url);
    }

    public static void clearCacheForGetRequest(Context context
            , String url
            , Map<String, String> params) {
        VolleyPlus.getInstance(context)
                .getRequestQueue()
                .getCache()
                .remove(VolleyUtils.buildGetRequestUrl(url, params));
    }

    public static void clearCacheForGetRequest(Context context
            , String url
            , JSONObject params) {
        VolleyPlus.getInstance(context)
                .getRequestQueue()
                .getCache()
                .remove(VolleyUtils.buildGetRequestUrl(url, params));
    }

    public static void clearCacheForGetRequest(Context context
            , String url
            , JSONArray params) {
        VolleyPlus.getInstance(context)
                .getRequestQueue()
                .getCache()
                .remove(VolleyUtils.buildGetRequestUrl(url, params));
    }

    /**
     * حذف تمامی کش(Cache)ها
     */
    public static boolean clearCache(Context context) {
        VolleyPlus instance = VolleyPlus.getInstance(context);
        if (instance != null) {
            RequestQueue queue = instance.getRequestQueue();
            if (queue != null) {
                Cache cache = queue.getCache();
                if (cache != null) {
                    cache.clear();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean cancelRequest(Context context, String request) {
        if (request != null) {
            //VolleyPlus.getInstance().getRequestQueue().cancelAll(request);
            if (getInstance(context).cancelPendingRequests(request))
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

    public static void parseVolleyError(VolleyError error, ParseVolleyErrorListener listener) {
        if (listener == null)
            return;
        if (error instanceof TimeoutError) {
            listener.onTimeoutError(error);
        } else if (error instanceof NoConnectionError) {
            listener.onNoConnectionError(error);
        } else if (error instanceof AuthFailureError) {
            listener.onAuthFailureError(error);
        } else if (error instanceof ClientError) {
            listener.onClientError(error);
        } else if (error instanceof ServerError) {
            listener.onServerError(error);
        } else if (error instanceof NetworkError) {
            listener.onNetworkError(error);
        } else if (error instanceof ParseError) {
            listener.onParseError(error);
        }
    }
}
