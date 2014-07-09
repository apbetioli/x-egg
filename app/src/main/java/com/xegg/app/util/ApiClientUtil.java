package com.xegg.app.util;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class ApiClientUtil {

    private static String getJsonFromURL(String url) {
        try {
            HttpGet get = new HttpGet(url);
            get.addHeader("Content-Type", "application/json");

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(get);

            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != 200)
                throw new IllegalStateException(String.valueOf(statusCode));

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            HttpEntity entity = response.getEntity();
            entity.writeTo(os);

            return os.toString();

        } catch (Exception e) {
            e.printStackTrace();
            //TODO
            throw new RuntimeException(e);
        }
    }

    private static void postJsonToURL(String json, String url) {
        try {
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(json));

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != 200)
                throw new IllegalStateException(String.valueOf(statusCode));

        } catch (Exception e) {
            e.printStackTrace();
            //TODO
            throw new RuntimeException(e);
        }
    }

    public static class GetPostsTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... param) {
            String tag = param.length > 0 ? param[0] : null;
            String url = Constants.URL_POSTS + (tag != null ? "?tag=" + tag : "");

            return getJsonFromURL(url);
        }
    }

    public static class GetTagsTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... param) {
            return getJsonFromURL(Constants.URL_TAGS);

        }
    }

    public static class SaveTask extends AsyncTask<JSONObject, Void, Void> {

        private String url;

        public SaveTask(String url) {
            this.url = url;
        }

        protected Void doInBackground(JSONObject... posts) {

            for (JSONObject post : posts) {
                postJsonToURL(post.toString(), url);
            }

            return null;
        }


    }

}
