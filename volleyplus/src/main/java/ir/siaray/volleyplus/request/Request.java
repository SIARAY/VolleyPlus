package ir.siaray.volleyplus.request;

import android.content.Context;

import ir.siaray.volleyplus.VolleyPlus;
import ir.siaray.volleyplus.util.Log;

/**
 * Created by SIARAY on 10/30/2017.
 */

public abstract class Request {

    public Request(Context context) {
        /*if (VolleyPlus.getInstance(context) == null) {
            VolleyPlus.initialize(context);
        }*/
        VolleyPlus.getInstance(context);
    }
}

