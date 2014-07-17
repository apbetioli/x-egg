package com.xegg.app.core;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.xegg.app.model.Post;
import com.xegg.app.model.Tag;
import com.xegg.app.util.Constants;

import java.util.List;

public class XEggApiClient {

    private Context context;

    XEggApiClient(Context context) {
        this.context = context;
    }

    public void savePost(Post post, FutureCallback<Post> future) {
        post.validate();

        Ion.with(context)
                .load(Constants.URL_POSTS)
                .setJsonObjectBody(post, new TypeToken<Post>() {
                })
                .as(new TypeToken<Post>() {
                })
                .setCallback(future);
    }

    public void listTags(FutureCallback<List<Tag>> future) {

        Ion.with(context)
                .load(Constants.URL_TAGS)
                .as(new TypeToken<List<Tag>>() {
                })
                .setCallback(future);
    }

    public void loadPosts(String tag, FutureCallback<List<Post>> future) {

        String tagParam = tag != null ? "?tag=" + tag : "";
        String url = Constants.URL_POSTS + tagParam;

        Ion.with(context)
                .load(url)
                .as(new TypeToken<List<Post>>() {
                })
                .setCallback(future);
    }

}
