package com.xegg.app.util;

import android.content.Context;
import android.provider.Settings;

public class AndroidUtil {

    public static String getUserId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
