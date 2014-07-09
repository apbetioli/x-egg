package com.xegg.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.xegg.app.model.Post;
import com.xegg.app.util.AndroidUtil;
import com.xegg.app.util.MessageUtil;

public class NewPostActivity extends BaseActivity {

    //TODO pegar das tags reais
    private String[] tags = new String[] 
    {
        "programming",
        "cats",
        "dogs",
        "girls",
        "world cup",
        "NSFW",
        "cosplay",
        "food",
        "Comic",
        "WTF",
        "gif"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        Spinner combo = (Spinner) findViewById(R.id.tag);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tags);
        combo.setAdapter(adapter);
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

        EditText descriptionEditText = (EditText) findViewById(R.id.description);
        EditText imageEditText = (EditText) findViewById(R.id.image);
        String author = AndroidUtil.getUserId(this);
        String language = AndroidUtil.getUserLocale();
        Spinner tag = (Spinner) findViewById(R.id.tag);

        Post post = new Post();
        post.setDescription(descriptionEditText.getText().toString());
        post.setImage(imageEditText.getText().toString());
        post.setAuthor(author);
        post.setLanguage(language);
        post.setTag(tag.getSelectedItem().toString());

        post.save();

        MessageUtil.handle(this, "Saved!");
    }



    private void redirectToNewPost() {
        //TODO
    }

}
