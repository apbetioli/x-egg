package com.xegg.app.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.koushikdutta.async.future.FutureCallback;
import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingRightInAnimationAdapter;
import com.xegg.app.R;
import com.xegg.app.core.XEgg;
import com.xegg.app.model.Tag;
import com.xegg.app.util.ColorCard;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


public class ListViewAnimationsFragment extends BaseFragment implements
        ActionBar.OnNavigationListener {

    private CardListView mListView;
    private CardArrayAdapter mCardArrayAdapter;

    @Override
    public int getTitleResourceId() {
        return R.string.app_name;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCard();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

    }



    private void initCard() {

        final ArrayList<Card> cards = new ArrayList<Card>();
        XEgg.with(this.getActivity()).listTags(new FutureCallback<List<Tag>>() {
            @Override
            public void onCompleted(Exception e, List<Tag> tags) {
                int i = 0;
                for (Tag tag : tags) {
                    ColorCard card = new ColorCard(getActivity());
                    card.setBackgroundResourceId(R.drawable.demoextra_card_selector_color2);
                    card.setTitle(tag.getName());
                    card.setTag(tag.getName());
                    cards.add(card);

                    switch (i++) {
                        case 0:
                            card.setBackgroundResourceId(R.drawable.demoextra_card_selector_color1);
                            break;
                        case 1:
                            card.setBackgroundResourceId(R.drawable.demoextra_card_selector_color2);
                            break;
                        case 2:
                            card.setBackgroundResourceId(R.drawable.demoextra_card_selector_color3);
                            break;
                        case 3:
                            card.setBackgroundResourceId(R.drawable.demoextra_card_selector_color4);
                            break;
                        case 4:
                            card.setBackgroundResourceId(R.drawable.demoextra_card_selector_color5);
                            i = 0;
                            break;
                    }
                }

                //Set the adapter
                mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

                mListView = (CardListView) getActivity().findViewById(R.id.carddemo_extra_list_viewanimations);
                if (mListView != null) {
                    setBottomRightAdapter();
                }
            }
        });

    }



    private void setAlphaAdapter() {
        AnimationAdapter animCardArrayAdapter = new AlphaInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter, mCardArrayAdapter);
        }
    }

    /**
     * Left animation
     */
    private void setLeftAdapter() {
        AnimationAdapter animCardArrayAdapter = new SwingLeftInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter, mCardArrayAdapter);
        }
    }

    /**
     * Right animation
     */
    private void setRightAdapter() {
        AnimationAdapter animCardArrayAdapter = new SwingRightInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter, mCardArrayAdapter);
        }
    }

    /**
     * Bottom animation
     */
    private void setBottomAdapter() {
        AnimationAdapter animCardArrayAdapter = new SwingBottomInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        mListView.setExternalAdapter(animCardArrayAdapter, mCardArrayAdapter);
    }

    /**
     * Bottom-right animation
     */
    private void setBottomRightAdapter() {
        AnimationAdapter animCardArrayAdapter = new SwingBottomInAnimationAdapter(new SwingRightInAnimationAdapter(mCardArrayAdapter));
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter, mCardArrayAdapter);
        }
    }

    /**
     * Scale animation
     */
    private void setScaleAdapter() {
        AnimationAdapter animCardArrayAdapter = new ScaleInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter, mCardArrayAdapter);
        }
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        switch (itemPosition) {
            case 0:
                setAlphaAdapter();
                return true;
            case 1:
                setLeftAdapter();
                return true;
            case 2:
                setRightAdapter();
                return true;
            case 3:
                setBottomAdapter();
                return true;
            case 4:
                setBottomRightAdapter();
                return true;
            case 5:
                setScaleAdapter();
                return true;
            default:
                return false;
        }

    }


}
