package com.xegg.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PostActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private PostPagerAdapter mPostPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        refresh();
    }

    private void refresh() {
        ApiClient.getContents(new ApiClient.Callback() {
            @Override
            public void result(String content) {

                if (content == null)
                    return;

                try {
                    JSONArray contentArray = new JSONArray(content);

                    mPostPagerAdapter = new PostPagerAdapter(getSupportFragmentManager(), contentArray);

                    // Set up the ViewPager with the sections adapter.
                    mViewPager = (ViewPager) findViewById(R.id.pager);
                    mViewPager.setAdapter(mPostPagerAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.refresh) {
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Call to update the build intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
//            Intent.createChooser(shareIntent, getResources().getText(R.string.build))
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    class PostPagerAdapter extends FragmentStatePagerAdapter {

        private JSONArray contentArray;

        public PostPagerAdapter(FragmentManager fm, JSONArray contentArray) {
            super(fm);
            this.contentArray = contentArray;
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return new PostFragment(position, contentArray.getJSONObject(position));

            } catch (JSONException e) {
                e.printStackTrace();
                return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return contentArray.length();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            try {
                return contentArray.getJSONObject(position).getString("description");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class PostFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        private JSONObject post;

        private AdView adView;

        public PostFragment (int sectionNumber, JSONObject post) {
            this(post);

            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            setArguments(args);
        }

        private PostFragment(JSONObject post) {
            this.post = post;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            try {
                loadDescription(rootView);

                loadImage(rootView);

                loadAd(rootView);

                updateShareIntent();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return rootView;
        }

        private void updateShareIntent() throws JSONException {

            ShareIntentBuilder shareIntentBuilder = new ShareIntentBuilder();
            shareIntentBuilder.withText(post.getString("description") + "\n" + post.getString("image_url"));
//            shareIntentBuilder.withSubject(menu.getString("description"));

            PostActivity.this.setShareIntent(shareIntentBuilder.build());
        }


        private void loadDescription(View rootView) throws JSONException {
            TextView textView = (TextView) rootView.findViewById(R.id.descricao);
            textView.setText(post.getString("description"));
        }

        private void loadImage(View rootView) throws JSONException {
            WebView webView = (WebView) rootView.findViewById(R.id.image);
            webView.loadUrl(post.getString("image_url"));
        }

        private void loadAd(View rootView) {
            adView = (AdView) rootView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
//                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                    .addTestDevice("samsung-gt_i9300-4df19510557b5fcf")
                    .build();
            adView.loadAd(adRequest);
        }

        @Override
        public void onResume() {
            super.onResume();
            if (adView != null) {
                adView.resume();
            }
        }

        @Override
        public void onPause() {
            if (adView != null) {
                adView.pause();
            }
            super.onPause();
        }

        @Override
        public void onDestroy() {
            if (adView != null) {
                adView.destroy();
            }
            super.onDestroy();
        }


    }

}
