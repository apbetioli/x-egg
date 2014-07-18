package com.xegg.app.fragments;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingRightInAnimationAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import it.gmariotti.cardslib.library.internal.CardArrayAdapter;

public class AnimationSelector {

    private Map<Integer, AnimationAdapter> animations = new HashMap<Integer, AnimationAdapter>();

    public AnimationSelector(CardArrayAdapter cardArrayAdapter) {
        animations.put(0, new AlphaInAnimationAdapter(cardArrayAdapter));
        animations.put(1, new SwingLeftInAnimationAdapter(cardArrayAdapter));
        animations.put(2, new SwingBottomInAnimationAdapter(cardArrayAdapter));
        animations.put(3, new SwingBottomInAnimationAdapter(new SwingRightInAnimationAdapter(cardArrayAdapter)));
        animations.put(4, new ScaleInAnimationAdapter(cardArrayAdapter));
    }

    public AnimationAdapter nextRandomAnimation() {
        Random random = new Random(System.currentTimeMillis());
        int chosen = random.nextInt(animations.size());
        return animations.get(chosen);
    }
}
