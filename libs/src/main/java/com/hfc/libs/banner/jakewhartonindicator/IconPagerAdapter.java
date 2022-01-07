package com.hfc.libs.banner.jakewhartonindicator;

import android.support.annotation.DrawableRes;

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
   @DrawableRes int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}
