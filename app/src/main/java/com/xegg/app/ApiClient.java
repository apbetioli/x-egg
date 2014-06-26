package com.xegg.app;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;

public class ApiClient {

    private static final String URL = "http://laughtime-apbetioli.rhcloud.com/posts.json";

    public static interface Callback {

        public void result(String content);

    }

    public static void getContents(final Callback callback) {

        new Thread() {

            @Override
            public void run() {

                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet get = new HttpGet(URL);
                    HttpResponse response = client.execute(get);

                    Log.i("REST", String.valueOf(response.getStatusLine()));

                    ByteArrayOutputStream os = new ByteArrayOutputStream();

                    HttpEntity entity = response.getEntity();
                    entity.writeTo(os);

                    String content = os.toString();

                    Log.i("REST", content);

                    callback.result(content);

                } catch (Exception e) {
                    Log.e("REST", "erro", e);

                    callback.result(null);
                }

            }
        }.start();

    }


}
