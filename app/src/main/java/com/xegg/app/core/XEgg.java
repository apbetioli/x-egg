package com.xegg.app.core;

import android.content.Context;
import android.widget.ImageView;

public class XEgg {

    public static XEggApiClient with(Context context) {
        return new XEggApiClient(context);
    }

    public static XEggImageViewer with(ImageView imageView) {
        return new XEggImageViewer(imageView);
    }

}
