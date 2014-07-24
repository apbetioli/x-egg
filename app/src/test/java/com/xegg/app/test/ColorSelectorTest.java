package com.xegg.app.test;

import com.xegg.app.R;

import junit.framework.TestCase;

public class ColorSelectorTest extends TestCase {

    public void testFirst() {
        ColorSelector colorSelector = new ColorSelector();
        int color = colorSelector.get("a");
        assertEquals(R.drawable.demoextra_card_selector_color1, color);
    }

}
