package com.xegg.app.fragments;

import com.xegg.app.R;

import java.util.HashMap;
import java.util.Map;

public class ColorSelector {
    private int i = 0;
    private Map<Integer, Integer> colors = new HashMap<Integer, Integer>();

    public ColorSelector() {
        colors.put(0, R.drawable.demoextra_card_selector_color1);
        colors.put(1, R.drawable.demoextra_card_selector_color2);
        colors.put(2, R.drawable.demoextra_card_selector_color3);
        colors.put(3, R.drawable.demoextra_card_selector_color4);
        colors.put(4, R.drawable.demoextra_card_selector_color5);
    }

    public int nextColor() {
        return colors.get(i++ % colors.size());
    }
}
