package com.xegg.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.xegg.app.model.Post;
import com.xegg.app.util.AndroidUtil;
import com.xegg.app.util.ApiClientUtil;
import com.xegg.app.util.MessageUtil;

import java.util.List;
import java.util.Locale;

public class NewPostActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        populateTags();
    }

    private void populateTags() {
        String[] tags = ApiClientUtil.listTagsAsArray();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tags);

        Spinner tagCombo = (Spinner) findViewById(R.id.tag);
        tagCombo.setAdapter(adapter);
    }


    private void saveAndRedirect() {
        saveNewPost();
        redirectToNewPost();
    }

    private void saveNewPost() {

        EditText descriptionEditText = (EditText) findViewById(R.id.description);
        EditText imageEditText = (EditText) findViewById(R.id.image);
        String author = AndroidUtil.getUserId(this);
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        String tag = ((Spinner) findViewById(R.id.tag)).getSelectedItem().toString();

        Post post = new Post();
        post.setDescription(descriptionEditText.getText().toString());
        post.setImage(imageEditText.getText().toString());
        post.setAuthor(author);
        post.setLanguage(language);
        post.setCountry(country);
        post.setTag(tag);

        ApiClientUtil.save(post);

        MessageUtil.handle(this, "Saved!");
    }



    private void redirectToNewPost() {
        //TODO
    }

}
