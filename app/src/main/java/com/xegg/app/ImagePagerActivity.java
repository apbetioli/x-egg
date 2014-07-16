package com.xegg.app;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.xegg.app.model.Post;
import com.xegg.app.util.Constants;
import com.xegg.app.util.MessageUtil;

import java.util.List;

public class ImagePagerActivity extends BaseActivity {

    private ViewPager pager;

    //TODO lidar com multiplas tags
    private int currentPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        setCurrentPage(savedInstanceState);

        getActionBar().setTitle(currentTag());

        fetchPosts();
    }

    private void setCurrentPage(Bundle savedInstanceState) {
        currentPage = savedInstanceState != null ? savedInstanceState.getInt(STATE_POSITION) : 0;
    }

    private void fetchPosts() {

        String tag = currentTag();
        String url = Constants.URL_POSTS + (tag != null ? "?tag=" + tag : "");

        Ion.with(this)
                .load(url)
                .as(new TypeToken<List<Post>>(){})
                .setCallback(new FutureCallback<List<Post>>() {
                    @Override
                    public void onCompleted(Exception e, List<Post> posts) {

                        if (posts.isEmpty()) {
                            //TODO I18N
                            MessageUtil.handle(ImagePagerActivity.this, "Nenhum post nesta categoria");
                            moveTaskToBack(true);
                            return;
                        }

                        loadPagerSavedState(posts);

                    }
                });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, pager.getCurrentItem());
    }

    private void loadPagerSavedState(List<Post> posts) {
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ImagePagerAdapter(posts));
        pager.setCurrentItem(currentPage);
    }

    private String currentTag() {
        Bundle bundle = getIntent().getExtras();
        return bundle != null ? bundle.getString(TAG) : null;
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private List<Post> posts;

        public ImagePagerAdapter(List<Post> posts) {
            this.posts = posts;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return posts.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = getLayoutInflater().inflate(R.layout.item_pager_image, view, false);
            view.addView(imageLayout, 0);

            ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);

            if (!posts.isEmpty()) {
                Post post = posts.get(position);

                Ion.with(imageView)
//                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.ic_error)
//                        .animateLoad(spinAnimation)
//                        .animateIn(fadeInAnimation)
                        .load(post.getImage());
            }

            return imageLayout;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

    }
}