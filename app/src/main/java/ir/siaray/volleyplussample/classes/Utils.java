package ir.siaray.volleyplussample.classes;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by SIAM on 9/3/2017.
 */

public class Utils {

    public static void showProgressDialog(ProgressBar progressbar) {
        if (progressbar.getVisibility() != View.VISIBLE) {
            progressbar.setVisibility(View.VISIBLE);
        }
    }

    public static void hideProgressDialog(ProgressBar progressbar) {
        if (progressbar.getVisibility() != View.INVISIBLE) {
            progressbar.setVisibility(View.INVISIBLE);
        }
    }
}
