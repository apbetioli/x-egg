package com.xegg.app.util;

import android.os.AsyncTask;

import com.xegg.app.model.Model;
import com.xegg.app.model.Post;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
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

    private static String getJsonFromURL(String url) {
        try {
            HttpGet get = new HttpGet(url);
            get.addHeader("Content-Type", "application/json");

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(get);

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

    //TODO private
    public static class GetPostsTask extends AsyncTask<String, Void, List<Post>> {

        protected List<Post> doInBackground(String... param) {
            String tag = param.length > 0 ? param[0] : null;
            String url = Constants.URL_POSTS + (tag != null ? "?tag=" + tag : "");

            List<Post> posts = new ArrayList<Post>();
            try {
                JSONArray postsArray = new JSONArray(getJsonFromURL(url));

                for (int i = 0; i < postsArray.length(); i++) {
                    JSONObject jsonObject = postsArray.getJSONObject(i);
                    Post post = new Post(jsonObject);
                    posts.add(post);
                }

            } catch (JSONException e) {
                //TODO
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            return posts;
        }
    }

    private static class FindTask extends AsyncTask<String, Void, String> {

        private final String url;

        public FindTask(String url) {
            this.url = url;
        }

        protected String doInBackground(String... param) {
            return getJsonFromURL(url);
        }
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
