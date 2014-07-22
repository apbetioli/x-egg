package com.xegg.app.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xegg.app.ImagePagerActivity;
import com.xegg.app.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Simple colored card
 *
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class ColorCard extends Card {

    protected String mTitle;
    protected int count;
    protected String tag;

    public ColorCard(Context context) {
        this(context, R.layout.card);
    }

    public ColorCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    private void init() {

        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Intent intent = new Intent(view.getContext(), ImagePagerActivity.class);
                intent.putExtra(Constants.TAG, tag);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        TextView title = (TextView) parent.findViewById(R.id.carddemo_extras_card_color_inner_simple_title);

        if (title != null) {
            title.setText(mTitle);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(0, 40, 0, 0);
            title.setLayoutParams(params);
            title.setTextSize(40);
            title.setTypeface(null, Typeface.BOLD);
            title.setAllCaps(true);
            title.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        }
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


}