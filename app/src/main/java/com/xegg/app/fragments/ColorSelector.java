package com.xegg.app.fragments;

import com.xegg.app.R;

import java.util.HashMap;
import java.util.Map;

public class ColorSelector {
    private Map<String, Integer> colors = new HashMap<String, Integer>();

    public ColorSelector() {
        colors.put("a", R.drawable.demoextra_card_selector_color1);
        colors.put("b", R.drawable.demoextra_card_selector_color2);
        colors.put("c", R.drawable.demoextra_card_selector_color3);
        colors.put("d", R.drawable.demoextra_card_selector_color4);
        colors.put("e", R.drawable.demoextra_card_selector_color5);
        colors.put("f", R.drawable.demoextra_card_selector_color1);
        colors.put("g", R.drawable.demoextra_card_selector_color2);
        colors.put("h", R.drawable.demoextra_card_selector_color3);
        colors.put("i", R.drawable.demoextra_card_selector_color4);
        colors.put("j", R.drawable.demoextra_card_selector_color5);
        colors.put("l", R.drawable.demoextra_card_selector_color1);
        colors.put("m", R.drawable.demoextra_card_selector_color2);
        colors.put("n", R.drawable.demoextra_card_selector_color3);
        colors.put("o", R.drawable.demoextra_card_selector_color4);
        colors.put("p", R.drawable.demoextra_card_selector_color5);
        colors.put("q", R.drawable.demoextra_card_selector_color1);
        colors.put("r", R.drawable.demoextra_card_selector_color2);
        colors.put("s", R.drawable.demoextra_card_selector_color3);
        colors.put("t", R.drawable.demoextra_card_selector_color4);
        colors.put("u", R.drawable.demoextra_card_selector_color5);
        colors.put("v", R.drawable.demoextra_card_selector_color1);
        colors.put("x", R.drawable.demoextra_card_selector_color2);
        colors.put("z", R.drawable.demoextra_card_selector_color3);
        colors.put("w", R.drawable.demoextra_card_selector_color4);
        colors.put("y", R.drawable.demoextra_card_selector_color5);
        colors.put("k", R.drawable.demoextra_card_selector_color1);
    }

    public int get(String name) {
        if (name.isEmpty())
            return R.drawable.demoextra_card_selector_color1;
        return colors.get(name.substring(0,1).toLowerCase());
    }


}
