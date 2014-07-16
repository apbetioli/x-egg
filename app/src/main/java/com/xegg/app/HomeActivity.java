package com.xegg.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xegg.app.util.ApiClientUtil;
import com.xegg.app.util.ColorsUtil;

public class HomeActivity extends BaseActivity {

    @Override
    public void onBackPressed() {
        ImageLoader.getInstance().stop();
        super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addTagButtonsToMainLayout();
    }

    private void addTagButtonsToMainLayout() {

        final LinearLayout main = (LinearLayout) findViewById(R.id.main);

        for (final String tag : ApiClientUtil.listTags()) {
            LinearLayout tagButton = createTagButton(tag);
            main.addView(tagButton);
        }
    }

    private LinearLayout createTagButton(final String tag) {
        final LinearLayout tagButton = new LinearLayout(this);
        tagButton.setBaselineAligned(true);
        tagButton.setClickable(true);
        tagButton.setOrientation(LinearLayout.HORIZONTAL);
        tagButton.setLayoutParams(createLayoutParamsForTagButton());
        tagButton.addView(createTagText(tag));
        tagButton.setBackground(createStatesForTagButton(tag));
        tagButton.setOnClickListener(onTagButtonClickListener(tag));
        return tagButton;
    }

    private LinearLayout.LayoutParams createLayoutParamsForTagButton() {
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = 220;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(0, 4, 0, 0);
        return params;
    }

    private TextView createTagText(String tag) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(createLayoutParamsForTagText());
        textView.setTextSize(40);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setAllCaps(true);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setText(tag);
        return textView;
    }

    private LinearLayout.LayoutParams createLayoutParamsForTagText() {
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(0, 6, 0, 0);
        return params;
    }

    private StateListDrawable createStatesForTagButton(String tag) {
        final int colorSelected = ColorsUtil.colors.get(tag.toLowerCase().substring(0, 1));

        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.gray));
        states.addState(new int[]{}, getResources().getDrawable(colorSelected));
        return states;
    }

    private View.OnClickListener onTagButtonClickListener(final String tag) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTag(tag);
            }
        };
    }

    private void openTag(String tag) {
        Intent intent = new Intent(this, ImagePagerActivity.class);
        intent.putExtra(TAG, tag);
        startActivity(intent);
    }
}