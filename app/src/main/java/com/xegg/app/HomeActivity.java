package com.xegg.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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