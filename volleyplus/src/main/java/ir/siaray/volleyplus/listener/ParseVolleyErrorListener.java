package ir.siaray.volleyplus.listener;

import com.android.volley.VolleyError;

/**
 * Created by SIAM on 11/3/2017.
 */

public interface ParseVolleyErrorListener {
    void onTimeoutError(VolleyError error);

    void onNoConnectionError(VolleyError error);

    void onAuthFailureError(VolleyError error);

    void onClientError(VolleyError error);

    void onServerError(VolleyError error);

    void onNetworkError(VolleyError error);

    void onParseError(VolleyError error);
}
