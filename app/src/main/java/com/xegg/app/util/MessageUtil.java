package com.xegg.app.util;

import android.content.Context;
import android.widget.Toast;


public class MessageUtil {

    public static void handle(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
