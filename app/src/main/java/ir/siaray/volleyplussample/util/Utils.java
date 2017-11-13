package ir.siaray.volleyplussample.util;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by SIAM on 9/3/2017.
 */

public class Utils {

    public static void showProgressBar(ProgressBar progressbar) {
        if (progressbar.getVisibility() != View.VISIBLE) {
            progressbar.setVisibility(View.VISIBLE);
        }
    }

    public static void hideProgressBar(ProgressBar progressbar) {
        if (progressbar.getVisibility() != View.INVISIBLE) {
            progressbar.setVisibility(View.INVISIBLE);
        }
    }
}
