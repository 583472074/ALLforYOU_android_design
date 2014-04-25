package com.myxilove.swipebacklayout.lib.app;

import com.myxilove.swipebacklayout.lib.SwipeBackLayout;
/**
 * @author xicheng
 */
public interface SwipeBackActivityBase {
    /**
     * @return the SwipeBackLayout associated with this activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    public abstract void scrollToFinishActivity();

}
