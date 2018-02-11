package ir.siaray.volleyplus.request;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import ir.siaray.volleyplus.VolleyPlus;
import ir.siaray.volleyplus.util.Log;
import ir.siaray.volleyplus.util.VolleyUtils;

import org.json.JSONArray;

import java.util.Map;

/**
 * Created by SIARAY on 9/15/2017.
 */

public class JsonArrayRequest extends ir.siaray.volleyplus.request.Request {

    private String mUrl;
    private Context mContext;
    private int mMethod = Request.Method.GET;
    private String mTag = JsonArrayRequest.class.getSimpleName();
    private Map<String, String> mHeader;
    private Request.Priority mPriority = Request.Priority.NORMAL;
    private int mTimeoutMs = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
    private int mNumberOfRetries = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
    private float mBackoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    //..............
    private Map<String, String> mObjectParams;
    private JSONArray mParams;
    private Listener<JSONArray> mListener;
    private ErrorListener mErrorListener;
    private byte[] mBody;
    private boolean mShouldCache = true;

    private JsonArrayRequest(Context context, String url) {
        super(context);
        mContext = context;
        mUrl = url;
    }

    //............
    public static JsonArrayRequest getInstance(Context context, String url) {
        return (new JsonArrayRequest(context, url));
    }

    public JsonArrayRequest setMethod(int method) {
        mMethod = method;
        return this;
    }

    public JsonArrayRequest setTag(String tag) {
        mTag = tag;
        return this;
    }

    public JsonArrayRequest setHeader(Map<String, String> header) {
        mHeader = header;
        return this;
    }

    public JsonArrayRequest setPriority(Request.Priority priority) {
        mPriority = priority;
        return this;
    }

    public JsonArrayRequest setTimeout(int timeoutMs) {
        mTimeoutMs = timeoutMs;
        return this;
    }

    public JsonArrayRequest setNumberOfRetries(int numberOfRetries) {
        mNumberOfRetries = numberOfRetries;
        return this;
    }

    public JsonArrayRequest setBackoffMultiplier(float backoffMultiplier) {
        mBackoffMultiplier = backoffMultiplier;
        return this;
    }

    public JsonArrayRequest setBody(byte[] body) {
        this.mBody = body;
        return this;
    }

    public JsonArrayRequest setParams(JSONArray params) {
        mParams = params;
        return this;
    }

    public JsonArrayRequest setParams(Map<String, String> params) {
        if (params != null && params.size() > 0) {
            mObjectParams = params;
            JSONArray jsonArray = new JSONArray();
            for (String key : params.keySet()) {
                jsonArray.put(params.get(key));
            }
            mParams = jsonArray;
        }
        return this;
    }

    public JsonArrayRequest setListener(Listener<JSONArray> listener, ErrorListener errorListener) {
        mListener = listener;
        mErrorListener = errorListener;
        return this;
    }


    public void send() {
        sendRequest();
    }

    private void sendRequest() {
        addParamsToGetRequest();
        com.android.volley.toolbox.JsonArrayRequest jsonArrayReq =
                new com.android.volley.toolbox.JsonArrayRequest(mMethod
                        , mUrl
                        , mParams
                        , mListener
                        , mErrorListener) {

                    @Override
                    public Priority getPriority() {
                        return mPriority;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return (mHeader != null) ? mHeader : (super.getHeaders());//new HashMap<String, String>());
                    }

                    @Override
                    public byte[] getBody() {
                        return (mBody != null) ? mBody : (super.getBody());
                    }
                };
        jsonArrayReq.setShouldCache(mShouldCache);
        VolleyPlus.setTimeoutRequest(jsonArrayReq
                , mTimeoutMs
                , mNumberOfRetries
                , mBackoffMultiplier);
        VolleyPlus.getInstance(mContext).addToRequestQueue(jsonArrayReq
                , mTag);
    }

    private void addParamsToGetRequest() {
        if (mMethod == Request.Method.GET) {
            if (mObjectParams != null) {
                mUrl = VolleyUtils.buildGetRequestUrl(mUrl, mObjectParams);
            } else {
                mUrl = VolleyUtils.buildGetRequestUrl(mUrl, mParams);
            }
        }
    }


    public JsonArrayRequest setShouldCache(boolean shouldCache) {
        mShouldCache = shouldCache;
        return this;
    }
}
