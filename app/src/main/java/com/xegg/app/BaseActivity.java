package com.xegg.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

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
            case R.id.new_post:
                openNewPostActivity();
                return true;
            case R.id.refresh:
                refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void refresh() {}

    protected void openNewPostActivity() {
        startActivity(new Intent(this, NewPostActivity.class));
    }
}
