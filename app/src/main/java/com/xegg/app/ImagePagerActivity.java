package com.xegg.app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xegg.app.model.Post;
import com.xegg.app.util.ApiClientUtil;
import com.xegg.app.util.MessageUtil;
import com.xegg.app.util.XEggImageView;

import java.util.List;

public class ImagePagerActivity extends BaseActivity {

    private DisplayImageOptions options;
    private ViewPager pager;
    private int currentPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        setCurrentPage(savedInstanceState);

        getActionBar().setTitle(currentTag());

        createDisplayImageOptions();

        fetchPosts();
    }

    private void setCurrentPage(Bundle savedInstanceState) {
        currentPage = savedInstanceState != null ? savedInstanceState.getInt(STATE_POSITION) : 0;
    }

    private void fetchPosts() {
        ApiClientUtil.GetPostsTask task = new ApiClientUtil.GetPostsTask() {

            @Override
            protected void onPostExecute(List<Post> posts) {
                super.onPostExecute(posts);

                if (posts.isEmpty()) {
                    //TODO I18N
                    MessageUtil.handle(ImagePagerActivity.this, "Nenhum post nesta categoria");
                    moveTaskToBack(true);
                    return;
                }

                loadPagerSavedState(posts);

            }
        };
        task.execute(currentTag());

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

    private void createDisplayImageOptions() {
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

            final ProgressBar loading = (ProgressBar) imageLayout.findViewById(R.id.loading);
            final XEggImageView imageView = (XEggImageView) imageLayout.findViewById(R.id.image);

            if (!posts.isEmpty()) {
                Post post = posts.get(position);
                ImageLoader.getInstance().displayImage(post.getImage(), imageView, options, new SimpleImageLoadingListener() {

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
                    public void onLoadingComplete(String imageUri, View view, Bitmap bitmap) {
                        loading.setVisibility(View.GONE);
//                        try {
//                            File file = ImageLoader.getInstance().getDiskCache().get(imageUri);
//                            ((XEggImageView) view).setAnimatedGif(new FileInputStream(file), XEggImageView.TYPE.FIT_CENTER);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        loading.setVisibility(View.GONE);
                    }
                });
            }

            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

    }
}