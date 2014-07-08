package com.xegg.app.util;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;

public class ApiClientUtil {

    private static final String URL_API = "http://www.x-egg.com/api/v1";
    private static final String URL_POSTS = URL_API + "/posts";
    private static final String URL_TAGS = URL_API + "/tags";

    private static String getJsonFromURL(String url) {
        try {
            HttpGet get = new HttpGet(url);
            get.addHeader("Content-Type", "application/json");

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(get);

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            HttpEntity entity = response.getEntity();
            entity.writeTo(os);

            return os.toString();

        } catch (Exception e) {
            //TODO
            throw new RuntimeException(e);
        }
    }

    public static class GetPostsTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... param) {
            String tag = param.length > 0 ? param[0] : null;
            String url = URL_POSTS + (tag != null ? "?tag=" + tag : "");

            return getJsonFromURL(url);
        }
    }

    public static class GetTagsTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... param) {
            return getJsonFromURL(URL_TAGS);

        }
    }


}
