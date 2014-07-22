package com.xegg.app.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.koushikdutta.async.future.FutureCallback;
import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.xegg.app.R;
import com.xegg.app.core.XEgg;
import com.xegg.app.model.Tag;
import com.xegg.app.util.ColorCard;
import com.xegg.app.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


public class ListViewAnimationsFragment extends BaseFragment {

    private AdView adView;

    @Override
    public int getTitleResourceId() {
        return R.string.app_name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState == null)
            loadTags();
    }

    public void loadTags() {

        XEgg.with(this.getActivity()).listTags(new FutureCallback<List<Tag>>() {
            @Override
            public void onCompleted(Exception e, List<Tag> tags) {
                if (e != null) {
                    MessageUtil.handle(getActivity(), "Error loading tags " + e);
                    return;
                }

                final List<Card> cards = createCardsFromTags(tags);
                createView(cards);

                loadAd();
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

    private void loadAd() {
        adView = (AdView) getActivity().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addKeyword("gif")
                .addKeyword("fun")
                .addKeyword("meme")
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("samsung-gt_i9300-4df19510557b5fcf")
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
