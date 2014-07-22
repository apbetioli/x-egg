package com.xegg.app;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.koushikdutta.async.future.FutureCallback;
import com.xegg.app.core.XEgg;
import com.xegg.app.model.Post;
import com.xegg.app.util.MessageUtil;

import java.util.List;

public class ImagePagerActivity extends BaseActivity {

    public static final String ID_ADS = "ca-app-pub-8685504932148148/7934956047";
    public static final int NUMBER_MODULE_FOR_SHOW_ADS = 5;
    private ViewPager pager;
    private InterstitialAd interstitial;

    //TODO lidar com multiplas tags
    private int currentPage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_pager, menu);
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        setCurrentPage(savedInstanceState);

        getActionBar().setTitle(currentTag());

        loadPosts();

        createInterstitialAd();


    }

    private void createInterstitialAd() {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(ID_ADS);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadAd();
            }
        });
        loadAd();
    }

    public void showAdsInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }


    void loadAd() {
        System.out.println("Loading aloha!!");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setCurrentPage(Bundle savedInstanceState) {
        currentPage = savedInstanceState != null ? savedInstanceState.getInt(STATE_POSITION) : 0;
    }

    private void loadPosts() {

        XEgg.with(this).loadPosts(currentTag(), new FutureCallback<List<Post>>() {
            @Override
            public void onCompleted(Exception e, List<Post> posts) {
                populatePosts(posts);
            }
        });

    }

    private void populatePosts(List<Post> posts) {
        if (posts.isEmpty()) {
            //TODO I18N
            MessageUtil.handle(this, "Nenhum post nesta categoria");
            onBackPressed();
            return;
        }

        loadPagerSavedState(posts);
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
            TextView description = (TextView) imageLayout.findViewById(R.id.description);

            if (!posts.isEmpty()) {
                Post post = posts.get(position);
                description.setText(post.getDescription());
                XEgg.with(imageView).loadImageFromPost(post);
            }

            if (position != 0 && position % NUMBER_MODULE_FOR_SHOW_ADS == 0)
                showAdsInterstitial();

            return imageLayout;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }


    }

}