package com.xegg.app.core;

import android.app.Application;

/**
 * Created by Thiago on 23/07/2014.
 */
public class GlobalClass extends Application {

    private int numberOfViewsOfAds = 0;


    public void visualizedOneImage() {
        numberOfViewsOfAds++;
    }

    public boolean isShowAds() {
        if (numberOfViewsOfAds > 0)
            return numberOfViewsOfAds % 50 == 0;
        return false;
    }
}
