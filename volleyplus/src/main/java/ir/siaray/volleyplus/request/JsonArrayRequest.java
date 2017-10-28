package ir.siaray.volleyplus.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import ir.siaray.volleyplus.VolleyPlus;

import org.json.JSONArray;

import java.util.Map;

/**
 * Created by SIARAY on 9/15/2017.
 */

public class JsonArrayRequest {

    private String mUrl;
    private Context mContext;
    private int mMethod = Request.Method.GET;
    private String mTag = JsonArrayRequest.class.getSimpleName();
    private Map<String, String> mHeader;
    private Request.Priority mPriority = Request.Priority.NORMAL;
    private int mTimeout = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
    private int mNumberOfRetries = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
    private float mBackoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    //..............
    private JSONArray mParams;
    private Listener<JSONArray> mListener;
    private ErrorListener mErrorListener;
    private byte[] mBody;

    private JsonArrayRequest(Context context, String url) {
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

    public JsonArrayRequest setTimeout(int timeout) {
        mTimeout = timeout;
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

    //............
    public void setmBody(byte[] mBody) {
        this.mBody = mBody;
    }

    public JsonArrayRequest setParams(JSONArray params) {
        mParams = params;
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
        com.android.volley.toolbox.JsonArrayRequest jsonObjReq =
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
                        return (mHeader != null) ? mHeader : (super.getHeaders()/*new HashMap<String, String>()*/);
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
