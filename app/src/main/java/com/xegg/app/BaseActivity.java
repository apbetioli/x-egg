package com.xegg.app;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xegg.app.util.Constants;

public abstract class BaseActivity extends Activity implements Constants {

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_clear_memory_cache:
                ImageLoader.getInstance().clearMemoryCache();
                return true;
            case R.id.item_clear_disc_cache:
                ImageLoader.getInstance().clearDiskCache();
                return true;
            default:
                return false;
        }
    }
}
