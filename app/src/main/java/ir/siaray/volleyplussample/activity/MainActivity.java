package ir.siaray.volleyplussample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import ir.siaray.volleyplus.listener.ParseVolleyErrorListener;
import ir.siaray.volleyplus.util.Log;
import ir.siaray.volleyplussample.R;
import ir.siaray.volleyplus.VolleyPlus;
import ir.siaray.volleyplus.request.JsonObjectRequest;
import ir.siaray.volleyplus.request.StringRequest;

import ir.siaray.volleyplussample.util.Constants;
import ir.siaray.volleyplussample.util.Strings;
import ir.siaray.volleyplussample.util.Utils;

import ir.siaray.volleyplus.request.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static ir.siaray.volleyplussample.util.Constants.REQUEST_BACK_OFF_MULTIPLAIER;
import static ir.siaray.volleyplussample.util.Constants.REQUEST_NUM_OF_RETRY;
import static ir.siaray.volleyplussample.util.Constants.REQUEST_TIME_OUT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String lastRequestTag = null;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        progressbar = findViewById(R.id.progressbar);
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
                cancelRequest();
                break;
        }
    }

    private void cancelRequest() {
        if (!VolleyPlus.cancelRequest(lastRequestTag)) {
            Toast.makeText(this, "Request is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, lastRequestTag + " Canceled", Toast.LENGTH_SHORT).show();
            Utils.hideProgressBar(progressbar);
            lastRequestTag = null;
        }
    }

    public void sendJsonStringRequest(String url, String reqTag) {
        lastRequestTag = reqTag;
        Utils.showProgressBar(progressbar);

        StringRequest.getInstance(this, url)
                .setTag(reqTag)
                //.setParams(getStringRequestParameters())
                .setMethod(Request.Method.GET)
                .setListener(new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showResponse(response);
                        Utils.hideProgressBar(progressbar);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error);
                        Utils.hideProgressBar(progressbar);
                    }
                })
                .send();

    }

    public void sendJsonArrayRequest(String url, String reqTag) {
        lastRequestTag = reqTag;
        Utils.showProgressBar(progressbar);

        JsonArrayRequest.getInstance(this, url)
                .setTag(reqTag)
                //.setParams(getJsonArrayParameters())
                .setMethod(Request.Method.GET)
                .setListener(new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        showResponse(response);
                        Utils.hideProgressBar(progressbar);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error);
                        Utils.hideProgressBar(progressbar);
                    }
                })
                .send();
    }

    private void sendJsonObjectRequest(String url, String reqTag) {
        lastRequestTag = reqTag;
        Utils.showProgressBar(progressbar);

        JsonObjectRequest.getInstance(this, url)
                .setTag(reqTag)
                //.setParams(getJsonObjectParameters())
                //.setHeader(getHeader())
                .setMethod(Request.Method.GET)
                .setTimeout(REQUEST_TIME_OUT)
                .setNumberOfRetries(REQUEST_NUM_OF_RETRY)
                .setBackoffMultiplier(REQUEST_BACK_OFF_MULTIPLAIER)
                .setPriority(Request.Priority.HIGH)
                .setListener(new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        showResponse(response);
                        Utils.hideProgressBar(progressbar);

                        //TestModel object = JSON.parseObject(response.toString(), TestModel.class);
                        //Log.printObject(object);
                        //Toast.makeText(MainActivity.this, "Email: " + object.getEmail(), Toast.LENGTH_SHORT).print();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error);
                        Utils.hideProgressBar(progressbar);
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

    private Map<String, String> getHeader() {
        Map<String, String>  headers = new HashMap<String, String>();
        headers.put("header1","value1");
        headers.put("header2","value2");
        return headers;
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


    private void showError(VolleyError error) {
        lastRequestTag = null;
        VolleyPlus.parseVolleyError(error, new ParseVolleyErrorListener() {
            @Override
            public void onTimeoutError(VolleyError error) {
                Log.print("TimeoutError: " + error.getMessage());
            }

            @Override
            public void onNoConnectionError(VolleyError error) {
                Log.print("NoConnectionError: " + error.getMessage());
            }

            @Override
            public void onAuthFailureError(VolleyError error) {
                Log.print("AuthFailureError: " + error.getMessage());
            }

            @Override
            public void onClientError(VolleyError error) {
                Log.print("ClientError: " + error.getMessage());
            }

            @Override
            public void onServerError(VolleyError error) {
                Log.print("ServerError: " + error.getMessage());
            }

            @Override
            public void onNetworkError(VolleyError error) {
                Log.print("NetworkError: " + error.getMessage());
            }

            @Override
            public void onParseError(VolleyError error) {
                Log.print("ParseError: " + error.getMessage());
            }
        });
        Toast.makeText(MainActivity.this
                , "Error: " + error.getMessage()
                        + " \ntime: " + error.getNetworkTimeMs()
                        + " \ncause: " + error.getCause()
                        + " \nlm: " + error.getLocalizedMessage()
                , Toast.LENGTH_LONG).show();
    }

    private void showResponse(Object response) {
        lastRequestTag = null;
        Log.print(response.toString());
        Toast.makeText(MainActivity.this
                , "response: " + response
                , Toast.LENGTH_LONG).show();
    }
}
