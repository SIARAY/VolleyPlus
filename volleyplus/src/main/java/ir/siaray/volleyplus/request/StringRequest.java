package ir.siaray.volleyplus.request;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response.*;

import ir.siaray.volleyplus.VolleyPlus;

import java.util.Map;

/**
 * Created by SIARAY on 9/15/2017.
 */

public class StringRequest extends ir.siaray.volleyplus.request.Request{

    private String mUrl;
    private Context mContext;
    private int mMethod = Request.Method.GET;
    private String mTag;
    private Map<String, String> mHeader;
    private Request.Priority mPriority = Request.Priority.NORMAL;
    private int mTimeout = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
    private int mNumberOfRetries = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
    private float mBackoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private Map<String, String> mParams;
    private Listener<String> mListener;
    private ErrorListener mErrorListener;
    private byte[] mBody;

    private StringRequest(Context context, String url) {
        super(context);
        mContext = context;
        mUrl = url;
    }

    public static StringRequest getInstance(Context context, String url) {
        return (new StringRequest(context, url));
    }

    public StringRequest setMethod(int method) {
        mMethod = method;
        return this;
    }

    public StringRequest setTag(String tag) {
        mTag = tag;
        return this;
    }

    public StringRequest setHeader(Map<String, String> header) {
        mHeader = header;
        return this;
    }

    public StringRequest setPriority(Request.Priority priority) {
        mPriority = priority;
        return this;
    }

    public StringRequest setTimeout(int timeout) {
        mTimeout = timeout;
        return this;
    }

    public StringRequest setNumberOfRetries(int numberOfRetries) {
        mNumberOfRetries = numberOfRetries;
        return this;
    }

    public StringRequest setBackoffMultiplier(float backoffMultiplier) {
        mBackoffMultiplier = backoffMultiplier;
        return this;
    }

    public StringRequest setParams(Map<String, String> params) {
        mParams = params;
        return this;
    }

    public StringRequest setListener(Listener<String> listener, ErrorListener errorListener) {
        mListener = listener;
        mErrorListener = errorListener;
        return this;
    }

    public StringRequest setBody(byte[] body) {
        this.mBody = body;
        return this;
    }

    public void send() {
        Log.i("VolleyPlus", "mMethod:" + mMethod +
                "\nmUrl:" + mUrl +
                "\nmTimeout:" + mTimeout +
                "\nmNumberOfRetries:" + mNumberOfRetries +
                "\nmBackoffMultiplier:" + mBackoffMultiplier +
                "\nmTag:" + mTag +
                "\nmListener:" + mListener +
                "\nmErrorListener:" + mErrorListener);
        sendRequest();
    }

    private void sendRequest() {
        com.android.volley.toolbox.StringRequest jsonStrReq =
                new com.android.volley.toolbox.StringRequest(mMethod
                        , mUrl
                        , mListener
                        , mErrorListener) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //return mParams;
                        return (mParams != null) ? mParams : (super.getParams());
                    }

                    @Override
                    public Priority getPriority() {
                        return mPriority;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return (mHeader != null) ? mHeader : (super.getHeaders()/*new HashMap<String, String>()*/);
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        //return super.getBody();
                        return (mBody != null) ? mBody : (super.getBody());
                    }

                };

        VolleyPlus.setTimeoutRequest(jsonStrReq
                , mTimeout
                , mNumberOfRetries
                , mBackoffMultiplier);
        VolleyPlus.getInstance().addToRequestQueue(jsonStrReq
                , mTag);

    }
}
