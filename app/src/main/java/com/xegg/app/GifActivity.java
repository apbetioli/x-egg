package com.xegg.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.xegg.app.R;
import com.xegg.app.util.AnimatedGifImageView;

public class GifActivity extends FragmentActivity implements View.OnClickListener {

    private AnimatedGifImageView animatedGifImageView;
    boolean switchMe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif2);
        animatedGifImageView = ((AnimatedGifImageView)findViewById(R.id.animatedGifImageView));
        animatedGifImageView.setAnimatedGif(R.raw.animated_gif,
                AnimatedGifImageView.TYPE.FIT_CENTER);
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
