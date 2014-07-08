package com.xegg.app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xegg.app.util.AnimatedGifImageView;
import com.xegg.app.util.ApiClientUtil;
import com.xegg.app.util.Constants;
import com.xegg.app.util.MessageUtil;

import org.json.JSONArray;
import org.json.JSONException;

public class ImagePagerActivity extends BaseActivity {

    private DisplayImageOptions options;
    private ViewPager pager;
    private AnimatedGifImageView animatedGifImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);

        getActionBar().setTitle("#"+currentTag());

        createOptions();

        createPager(savedInstanceState);

        loadImages();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(PAGE, pager.getCurrentItem());
    }

    private void createPager(Bundle savedInstanceState) {
        pager = (ViewPager) findViewById(R.id.pager);

        if (savedInstanceState != null)
            pager.setCurrentItem(savedInstanceState.getInt(PAGE, 0));
    }

    private void createOptions() {
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    private void loadImages() {

        ApiClientUtil.GetPostsTask task = new ApiClientUtil.GetPostsTask() {
            @Override
            protected void onPostExecute(String postsString) {

                try {
                    JSONArray postsArray = new JSONArray(postsString);

                    //TODO melhorar
                    if(postsArray.length() == 0) {
                        MessageUtil.handle(ImagePagerActivity.this, "Nenhum post nesta categoria");
                        return;
                    }

                    pager.setAdapter(new ImagePagerAdapter(postsArray));

                } catch (Exception e) {
                    //TODO melhorar
                    e.printStackTrace();
                    MessageUtil.handle(ImagePagerActivity.this, "Erro ao converter dados: " + e.getMessage());
                }
            }
        };

        task.execute(currentTag());
    }

    private String currentTag() {
        Bundle bundle = getIntent().getExtras();
        return bundle != null ? bundle.getString(TAG) : null;
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private final JSONArray postArray;
        private final LayoutInflater inflater;

        ImagePagerAdapter(JSONArray postArray) {
            this.postArray = postArray;
            inflater = getLayoutInflater();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return postArray.length();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);
            view.addView(imageLayout, 0);

            final ImageView image = (ImageView) imageLayout.findViewById(R.id.image);
            final ProgressBar loading = (ProgressBar) imageLayout.findViewById(R.id.loading);
            loading.setVisibility(View.VISIBLE);

            try {
                String uri = postArray.getJSONObject(position).getString(Constants.ATR_IMAGE);

                ImageLoader.getInstance().displayImage(uri, image, options, new SimpleImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        loading.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        MessageUtil.handle(ImagePagerActivity.this, failReason);

                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        loading.setVisibility(View.GONE);
                    }
                });

            } catch (JSONException e) {
                //TODO tratar
                e.printStackTrace();
                MessageUtil.handle(ImagePagerActivity.this, "Erro ao converter dados: " + e.getMessage());
            }

            return imageLayout;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
/*
        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
        */
    }
}