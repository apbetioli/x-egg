package com.xegg.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xegg.app.util.Constants;

public abstract class BaseActivity extends ActionBarActivity implements Constants {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //TODO mover para settings
            case R.id.item_clear_memory_cache:
                ImageLoader.getInstance().clearMemoryCache();
                return true;
            case R.id.item_clear_disc_cache:
                ImageLoader.getInstance().clearDiskCache();
                return true;

            case R.id.new_post:
                openNewPost();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return false;
        }
    }

    private void openSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    private void openNewPost() {
        startActivity(new Intent(this, NewPostActivity.class));
    }
}
