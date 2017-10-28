package ir.siaray.volleyplus.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import ir.siaray.volleyplus.VolleyPlus;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by SIARAY on 9/15/2017.
 */

public class JsonObjectRequest {

    private String mUrl;
    private Context mContext;
    private int mMethod = Request.Method.GET;
    private String mTag = JsonObjectRequest.class.getSimpleName();
    private Map<String, String> mHeader;
    private Request.Priority mPriority = Request.Priority.NORMAL;
    private int mTimeout = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
    private int mNumberOfRetries = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
    private float mBackoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    //..............
    private JSONObject mParams;
    private Listener<JSONObject> mListener;
    private ErrorListener mErrorListener;
    private byte[] mBody;

    private JsonObjectRequest(Context context, String url) {
        mContext = context;
        mUrl = url;
    }

    //............
    public static JsonObjectRequest getInstance(Context context, String url) {
        return (new JsonObjectRequest(context, url));
    }

    public JsonObjectRequest setMethod(int method) {
        mMethod = method;
        return this;
    }

    public JsonObjectRequest setTag(String tag) {
        mTag = tag;
        return this;
    }

    public JsonObjectRequest setHeader(Map<String, String> header) {
        mHeader = header;
        return this;
    }

    public JsonObjectRequest setPriority(Request.Priority priority) {
        mPriority = priority;
        return this;
    }

    public JsonObjectRequest setTimeout(int timeout) {
        mTimeout = timeout;
        return this;
    }

    public JsonObjectRequest setNumberOfRetries(int numberOfRetries) {
        mNumberOfRetries = numberOfRetries;
        return this;
    }

    public JsonObjectRequest setBackoffMultiplier(float backoffMultiplier) {
        mBackoffMultiplier = backoffMultiplier;
        return this;
    }

    //............

    public void setmBody(byte[] mBody) {
        this.mBody = mBody;
    }

    public JsonObjectRequest setParams(JSONObject params) {
        mParams = params;
        return this;
    }

    public JsonObjectRequest setListener(Listener<JSONObject> listener, ErrorListener errorListener) {
        mListener = listener;
        mErrorListener = errorListener;
        return this;
    }


    public void send() {
        sendRequest();
    }

    private void sendRequest() {
        com.android.volley.toolbox.JsonObjectRequest jsonObjReq =
                new com.android.volley.toolbox.JsonObjectRequest(mMethod
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
                        return (mHeader != null) ? mHeader : (super.getHeaders());
                    }

                    @Override
                    public byte[] getBody(){
                        return (mBody != null) ? mBody : (super.getBody());
                    }
                };

        VolleyPlus.setTimeoutRequest(jsonObjReq
                , mTimeout
                , mNumberOfRetries
                , mBackoffMultiplier);
        VolleyPlus.getInstance().addToRequestQueue(jsonObjReq
                , mTag);
    }
}
