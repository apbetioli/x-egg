package com.xegg.app.util;

import android.content.Context;
import android.provider.Settings;

import java.util.Locale;

public class AndroidUtil {

    public static String getUserLocale() {
        return Locale.getDefault().getLanguage() + "_" +  Locale.getDefault().getCountry();
    }

    public static String getUserId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
