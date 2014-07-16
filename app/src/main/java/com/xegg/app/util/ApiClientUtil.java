package com.xegg.app.util;

import android.os.AsyncTask;

import com.xegg.app.model.Model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ApiClientUtil {

    public static void save(Model m) {
        try {
            m.validate();
            ApiClientUtil.SaveTask task = new ApiClientUtil.SaveTask(m.url());
            task.execute(m.toJSONObject());
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String postJsonToURL(String json, String url) {
        try {
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(json));

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200)
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

    //TODO listar tags reais
    public static List<String> listTags() {
        List<String> tags = new ArrayList<String>();
        tags.add("programming");
        tags.add("cats");
        tags.add("dogs");
        tags.add("girls");
        tags.add("world cup");
        tags.add("NSFW");
        tags.add("cosplay");
        tags.add("food");
        tags.add("Comic");
        tags.add("WTF");
        tags.add("gif");
        return tags;
    }

    public static String[] listTagsAsArray() {
        List<String> tags = listTags();
        return tags.toArray(new String[tags.size()]);
    }

    private static class SaveTask extends AsyncTask<JSONObject, Void, String> {

        private final String url;

        public SaveTask(String url) {
            this.url = url;
        }

        protected String doInBackground(JSONObject... posts) {

            for (JSONObject post : posts) {
                postJsonToURL(post.toString(), url);
            }

            return null;
        }


    }

}
