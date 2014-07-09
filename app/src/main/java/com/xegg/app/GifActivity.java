package com.xegg.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.xegg.app.R;
import com.xegg.app.util.AnimatedGifImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GifActivity extends FragmentActivity implements View.OnClickListener {

    private AnimatedGifImageView animatedGifImageView;
    boolean switchMe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif2);
        animatedGifImageView = ((AnimatedGifImageView) findViewById(R.id.animatedGifImageView));
        try {
            URL gifURL = new URL("http://i.imgur.com/9PU5XNV.gif");
            HttpURLConnection connection = (HttpURLConnection) gifURL.openConnection();


            animatedGifImageView.setAnimatedGif(connection.getInputStream(),
                    AnimatedGifImageView.TYPE.FIT_CENTER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((Button) findViewById(R.id.button1)).setOnClickListener(this);
        switchMe = true;
    }

    @Override
    public void onClick(View v) {
        if (!switchMe)
            animatedGifImageView.setAnimatedGif(R.raw.animated_gif_big,
                    AnimatedGifImageView.TYPE.STREACH_TO_FIT);
        else
            animatedGifImageView.setImageResource(R.drawable.ic_launcher);
        switchMe = !switchMe;
    }
}
