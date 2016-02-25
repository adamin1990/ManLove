package com.adamin.manslove.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Toast;


/**
 * Created by Junhoi on 2015. 4. 24..
 */
public class SnackBarUtils {

    public final static int SUCCESS = 0;
    public final static int WARNING = 1;
    public final static int ERROR = 2;
    public final static int INFO = 3;

    private final static int COLOR_SUCCESS = Color.rgb(45, 156, 114);
    private final static int COLOR_WARNING = Color.rgb(237, 145, 39);
    private final static int COLOR_ERROR = Color.rgb(220, 74, 53);
    private final static int COLOR_INFO = Color.rgb(64, 137, 200);

    public static void showSnackBar(Context context, int messageRes, int type) {
        showSnackBar(context, context.getApplicationContext().getString(messageRes), type);
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void showSnackBar(Context context, String message, int type) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            showSnackBar(view, message, type);
            return;
        }
        if(context!=null){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        }
    }

    public static void showSnackBar(DialogFragment dialogFragment, String message, int type) {
        showSnackBar(dialogFragment.getDialog().getWindow().getDecorView(), message, type);
    }

    public static void showSnackBar(Dialog dialog, String message, int type) {
        showSnackBar(dialog.getWindow().getDecorView(), message, type);
    }

    public static void showSnackBar(View view, String message, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        switch (type) {
            case SUCCESS:
                snackbar.getView().setBackgroundColor(COLOR_SUCCESS);
                break;
            case WARNING:
                snackbar.getView().setBackgroundColor(COLOR_WARNING);
                break;
            case ERROR:
                snackbar.getView().setBackgroundColor(COLOR_ERROR);
                break;
            case INFO:
                snackbar.getView().setBackgroundColor(COLOR_INFO);
                break;
        }
        snackbar.show();
    }
}
