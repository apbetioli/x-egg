package com.xegg.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final LinearLayout l = (LinearLayout) findViewById(R.id.btn1);

        l.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImagePagerActivity.class);
                intent.putExtra(TAG, "programming"); //TODO pegar da categoria selecionada na tela
                startActivity(intent);
            }


            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        l.setBackgroundColor(Color.RED);
                        break;
                    case MotionEvent.ACTION_UP:

                        //set color back to default
                        l.setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
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