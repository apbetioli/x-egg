package com.xegg.app.core;

import android.content.Context;
import android.widget.ImageView;

import com.xegg.app.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class XEgg {

    public static XEggApiClient with(Context context) {
        return new XEggApiClient(context);
    }

    public static XEggImageViewer with(ImageView imageView) {
        return new XEggImageViewer(imageView);
    }

    public static String[] toStringArray(List<Tag> tags) {

        List<String> tagNames = new ArrayList<String>();
        for (Tag tag : tags) {
            tagNames.add(tag.getName());
        }

        return tagNames.toArray(new String[tagNames.size()]);

    }
}
