package com.example.ipandaitems.view.livechina.assist;

public interface LiveChinaIconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}