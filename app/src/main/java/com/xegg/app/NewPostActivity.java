package com.xegg.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xegg.app.util.MessageUtil;

public class NewPostActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveAndRedirect();
                return true;
            default:
                return false;
        }
    }

    private void saveAndRedirect() {
        saveNewPost();
        redirectToNewPost();
    }

    private void saveNewPost() {
        //TODO
        MessageUtil.handle(this, "Saved!");
    }

    private void redirectToNewPost() {
        //TODO
    }

}
