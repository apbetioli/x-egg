package com.xegg.app.util;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;

public class ApiClientUtil {

    private static final String URL = "http://laughtime-apbetioli.rhcloud.com/posts.json";

    public static class GetPostsTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... param) {
            try {
                String tag = param.length > 0 ? param[0] : null;
                String url = URL + (tag != null ? "?tag=" + tag : "");

                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
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
    }


}
