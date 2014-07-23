package com.xegg.app.core;

import android.content.Intent;

public class ShareIntentBuilder {

    private final Intent intent = new Intent();
    private String text;

    public ShareIntentBuilder withText(String text) {
        intent.putExtra(Intent.EXTRA_TEXT, text + "\n\nShared by: http://www.x-egg.com");
        return this;
    }

    public ShareIntentBuilder withSubject(String subject) {
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        return this;
    }

    public Intent build() {
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        return intent;
    }


}
