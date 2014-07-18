package com.xegg.app.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koushikdutta.async.future.FutureCallback;
import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
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


public class ListViewAnimationsFragment extends BaseFragment {

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

        loadTags();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

    }

    private void loadTags() {

        XEgg.with(this.getActivity()).listTags(new FutureCallback<List<Tag>>() {
            @Override
            public void onCompleted(Exception e, List<Tag> tags) {
                final List<Card> cards = createCardsFromTags(tags);
                createView(cards);
            }
        });

    }

    private void createView(List<Card> cards) {
        CardListView cardListView = (CardListView) getActivity().findViewById(R.id.card_list);

        CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        AnimationSelector animationSelector = new AnimationSelector(cardArrayAdapter);

        AnimationAdapter animationAdapter = animationSelector.nextRandomAnimation();

        animationAdapter.setAbsListView(cardListView);

        cardListView.setExternalAdapter(animationAdapter, cardArrayAdapter);
    }


    private List<Card> createCardsFromTags(List<Tag> tags) {
        final List<Card> cards = new ArrayList<Card>();

        ColorSelector colorSelector = new ColorSelector();

        for (Tag tag : tags) {
            ColorCard card = new ColorCard(getActivity());
            card.setBackgroundResourceId(R.drawable.demoextra_card_selector_color2);
            card.setTitle(tag.getName());
            card.setTag(tag.getName());
            card.setBackgroundResourceId(colorSelector.nextColor());

            cards.add(card);
        }

        return cards;
    }

}
