package com.xegg.app;

import android.content.Intent;

/**
 * Created by alexandre on 19/06/14.
 */
public class ShareIntentBuilder {

    private final Intent intent = new Intent();
    private String text;

    public ShareIntentBuilder withText(String text) {
        intent.putExtra(Intent.EXTRA_TEXT, text);
        return this;
    }

    public ShareIntentBuilder withSubject(String subject) {
        intent.putExtra(Intent.EXTRA_TEXT, subject);
        return this;
    }

    public Intent build() {
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        return intent;
    }


}
