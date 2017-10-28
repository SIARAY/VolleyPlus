package ir.siaray.volleyplussample.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import ir.siaray.volleyplussample.R;
import ir.siaray.volleyplus.VolleyPlus;
import ir.siaray.volleyplus.request.JsonObjectRequest;
import ir.siaray.volleyplus.request.StringRequest;

import ir.siaray.volleyplussample.classes.Constants;
import ir.siaray.volleyplussample.classes.Strings;
import ir.siaray.volleyplussample.classes.Utils;

import ir.siaray.volleyplus.request.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;
    private String lastRequest = null;
    private ProgressBar progressbar;
    private Request.Priority priority = Request.Priority.HIGH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        progressDialog = new ProgressDialog(this);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        (findViewById(R.id.btnJsonObject)).setOnClickListener(this);
        (findViewById(R.id.btnJsonArray)).setOnClickListener(this);
        (findViewById(R.id.btnJsonString)).setOnClickListener(this);
        (findViewById(R.id.btnCancelRequest)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnJsonObject:
                sendJsonObjectRequest(Constants.jsonObjectUrl
                        , Strings.TAG_JSON_OBJECT);

                break;
            case R.id.btnJsonArray:
                sendJsonArrayRequest(Constants.jsonArrayUrl
                        , Strings.TAG_JSON_ARRAY);

                break;
            case R.id.btnJsonString:
                sendJsonStringRequest(Constants.jsonStringUrl
                        , Strings.TAG_JSON_STRING);
                break;
            case R.id.btnCancelRequest:
                if (!VolleyPlus.cancelRequest(lastRequest)) {
                    Toast.makeText(this, "Request is null", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                    Utils.hideProgressDialog(progressbar);
                }
                break;
        }
    }

    public void sendJsonStringRequest(String url, String reqTag) {
        lastRequest = reqTag;
        Utils.showProgressDialog(progressbar);

        //StringRequest stringRequest=
        StringRequest.getInstance(this, url)
                .setTag(reqTag)
                .setParams(getStringRequestParameters())
                .setMethod(Request.Method.POST)
                .setListener(new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showResponseToast(response);
                        Utils.hideProgressDialog(progressbar);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showErrorToast(error);
                        Utils.hideProgressDialog(progressbar);
                    }
                })
                .send();

    }

    public void sendJsonArrayRequest(String url, String reqTag) {
        lastRequest = reqTag;
        Utils.showProgressDialog(progressbar);

        JsonArrayRequest.getInstance(this, url)
                .setTag(reqTag)
                .setParams(getJsonArrayParameters())
                .setMethod(Request.Method.POST)
                .setListener(new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        showResponseToast(response);
                        Utils.hideProgressDialog(progressbar);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showErrorToast(error);
                        Utils.hideProgressDialog(progressbar);
                    }
                })
                .send();
    }

    private void sendJsonObjectRequest(String url, String reqTag) {
        lastRequest = reqTag;
        Utils.showProgressDialog(progressbar);

        JsonObjectRequest.getInstance(this, url)
                .setTag(reqTag)
                .setParams(getJsonObjectParameters())
                .setMethod(Request.Method.POST)
                .setListener(new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        showResponseToast(response);
                        Utils.hideProgressDialog(progressbar);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showErrorToast(error);
                        Utils.hideProgressDialog(progressbar);
                    }
                })
                .send();
    }

    private Map<String, String> getStringRequestParameters() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "Androidhive");
        params.put("email", "abc@androidhive.info");
        params.put("password", "password123");
        return params;
    }

    private JSONObject getJsonObjectParameters() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "Androidhive");
        params.put("email", "abc@androidhive.info");
        params.put("password", "password123");
        return new JSONObject(params);
    }

    private JSONArray getJsonArrayParameters() {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.put(0, "Androidhive");
            jsonArray.put(1, "abc@androidhive.info");
            jsonArray.put(2, "password123");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    private void showErrorToast(VolleyError error) {
        if (error instanceof TimeoutError) {
            Log.d(Strings.TAG, "TimeoutError: " + error.getMessage());
        } else if (error instanceof NoConnectionError) {
            Log.d(Strings.TAG, "NoConnectionError: " + error.getMessage());
        } else if (error instanceof AuthFailureError) {
            Log.d(Strings.TAG, "AuthFailureError: " + error.getMessage());
        } else if (error instanceof ServerError) {
            Log.d(Strings.TAG, "ServerError: " + error.getMessage());
        } else if (error instanceof NetworkError) {
            Log.d(Strings.TAG, "NetworkError: " + error.getMessage());
        } else if (error instanceof ParseError) {
            Log.d(Strings.TAG, "ParseError: " + error.getMessage());
        }
        Toast.makeText(MainActivity.this
                , "Error: " + error.getMessage()
                        + " \ntime: " + error.getNetworkTimeMs()
                        + " \ncause: " + error.getCause()
                        + " \nlm: " + error.getLocalizedMessage()
                        + " \nst: " + error.getStackTrace()
                , Toast.LENGTH_LONG).show();
    }

    private void showResponseToast(Object response) {
        Log.d(Strings.TAG, response.toString());
        Toast.makeText(MainActivity.this
                , "response: " + response
                , Toast.LENGTH_LONG).show();
    }
}
