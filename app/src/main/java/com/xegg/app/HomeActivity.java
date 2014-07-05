package com.xegg.app;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.xegg.app.R.drawable.green;
import static com.xegg.app.R.drawable.red;
import static com.xegg.app.R.drawable.green;
import static com.xegg.app.R.drawable.blue;

public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final LinearLayout main = (LinearLayout) findViewById(R.id.principal);
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
        int color[] = {red, blue, green};

        for (String tag : tags) {
            final String tagRox = tag;
            final LinearLayout child = new LinearLayout(this);
            Random random = new Random();
            final int colorSelected = color[random.nextInt(color.length)];
            child.setClickable(true);
            child.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 220);
            params.setMargins(0, 4, 0, 0);
            child.setLayoutParams(params);
           // child.setBackground(getResources().getDrawable(colorSelected));
            child.setBaselineAligned(true);

            TextView tv1 = new TextView(this);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 6, 0, 0);
            tv1.setLayoutParams(params);
            tv1.setTextSize(40);
            tv1.setTypeface(null, Typeface.BOLD);
            tv1.setAllCaps(true);
            tv1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            tv1.setTextColor(getResources().getColor(R.color.white));
            tv1.setText(tagRox);
            child.addView(tv1);

            main.addView(child);

            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.blue));
            //states.addState(new int[]{android.R.attr.state_focused}, getResources().getDrawable(R.drawable.green));
            states.addState(new int[]{}, getResources().getDrawable(colorSelected));

            child.setBackground(states);
            child.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ImagePagerActivity.class);
                    intent.putExtra(TAG, tagRox); //TODO pegar da categoria selecionada na tela
                    startActivity(intent);


                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        ImageLoader.getInstance().stop();
        super.onBackPressed();
    }

    public void onImagePagerClick(View view) {
        Intent intent = new Intent(this, ImagePagerActivity.class);
        intent.putExtra(TAG, "programming"); //TODO pegar da categoria selecionada na tela
        startActivity(intent);
    }
}