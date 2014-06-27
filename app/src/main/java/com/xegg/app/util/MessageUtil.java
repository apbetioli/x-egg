package com.xegg.app.util;

import android.content.Context;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.assist.FailReason;

public class MessageUtil {

    public static void handle(Context context, FailReason failReason) {
        String message = null;
        switch (failReason.getType()) {
            case IO_ERROR:
                message = "Input/Output error";
                break;
            case DECODING_ERROR:
                message = "Image can't be decoded";
                break;
            case NETWORK_DENIED:
                message = "Downloads are denied";
                break;
            case OUT_OF_MEMORY:
                message = "Out Of Memory error";
                break;
            case UNKNOWN:
                message = "Unknown error";
                break;
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void handle(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
