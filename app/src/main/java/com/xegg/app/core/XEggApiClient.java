package com.xegg.app.core;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.libcore.RawHeaders;
import com.koushikdutta.ion.HeadersCallback;
import com.koushikdutta.ion.Ion;
import com.xegg.app.model.Post;
import com.xegg.app.model.Tag;
import com.xegg.app.util.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class XEggApiClient {

    private Context context;

    XEggApiClient(Context context) {
        this.context = context;
    }

    public void savePost(Post post, FutureCallback<Post> future) {
        try {
            post.validate();
        } catch (Exception e) {
            future.onCompleted(e, null);
            return;
        }

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

        String tagParam = null;
        try {
            tagParam = tag != null ? "?tag=" + URLEncoder.encode(tag, "UTF-8") : "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            tagParam = tag;
        }

        String url = Constants.URL_POSTS + tagParam;

        Ion.with(context)
                .load(url)
                .as(new TypeToken<List<Post>>() {
                })
                .setCallback(future);
    }

    //TODO
    public boolean test() throws IOException {

        String url = "";

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(new HttpHead(url));
        return response.getFirstHeader("Content-Type").toString().startsWith("image/");

    }
}
