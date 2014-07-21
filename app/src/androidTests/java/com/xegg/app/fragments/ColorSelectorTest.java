package com.xegg.app.fragments;

import com.xegg.app.R;

import junit.framework.TestCase;

public class ColorSelectorTest extends TestCase {

    public void testFirst() {
        ColorSelector colorSelector = new ColorSelector();
        int color = colorSelector.nextColor();
        assertEquals(R.drawable.demoextra_card_selector_color1, color);
    }

}
