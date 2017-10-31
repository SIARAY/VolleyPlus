package ir.siaray.volleyplus.request;

import android.content.Context;

import ir.siaray.volleyplus.VolleyPlus;

/**
 * Created by SIARAY on 10/30/2017.
 */

public class Request {
    public Request(Context context) {
        if (VolleyPlus.getInstance() == null) {
            VolleyPlus.initialize(context);
        }
    }
}

