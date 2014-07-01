package com.xegg.app.util;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;

public class ApiClientUtil {

    private static final String URL = "http://www.x-egg.com/api/v1/posts";

    public static class GetPostsTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... param) {
            try {
                String tag = param.length > 0 ? param[0] : null;
                String url = URL;// + (tag != null ? "?tag=" + tag : "");

                HttpGet get = new HttpGet(url);
                get.addHeader("Content-Type", "application/json");

                HttpClient client = new DefaultHttpClient();
                HttpResponse response = client.execute(get);

                ByteArrayOutputStream os = new ByteArrayOutputStream();

                HttpEntity entity = response.getEntity();
                entity.writeTo(os);

                String content = os.toString();
                return content;

            } catch (Exception e) {
                //TODO
                throw new RuntimeException(e);
            }
        }
    }


}
