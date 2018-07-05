package ir.siaray.volleyplus.request;

import android.content.Context;

import ir.siaray.volleyplus.VolleyPlus;

/**
 * Created by SIARAY on 10/30/2017.
 */

public abstract class Request {

    public Request(Context context) {

        VolleyPlus.getInstance(context);
    }
}

