package com.xegg.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.koushikdutta.async.future.FutureCallback;
import com.xegg.app.core.XEgg;
import com.xegg.app.model.Post;
import com.xegg.app.model.Tag;
import com.xegg.app.util.AndroidUtil;
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
                saveNewPost();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        loadTags();
    }

    private void loadTags() {
        XEgg.with(this).listTags(new FutureCallback<List<Tag>>() {
            @Override
            public void onCompleted(Exception e, List<Tag> tags) {
                populateTags(tags);
            }
        });
    }

    private void populateTags(List<Tag> tags) {
        String[] tagArray = tags.toArray(new String[tags.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tagArray);

        Spinner tagCombo = (Spinner) findViewById(R.id.tag);
        tagCombo.setAdapter(adapter);
    }

    private void saveNewPost() {

        Post post = incarnatePost();

        XEgg.with(this).savePost(post, new FutureCallback<Post>() {
            @Override
            public void onCompleted(Exception e, Post newPost) {
                if (e != null)
                    MessageUtil.handle(NewPostActivity.this, "Error saving post " + e);
                else
                    MessageUtil.handle(NewPostActivity.this, "Saved with id " + newPost.getCreated());

            }
        });
    }

    private Post incarnatePost() {
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
        return post;
    }

}
