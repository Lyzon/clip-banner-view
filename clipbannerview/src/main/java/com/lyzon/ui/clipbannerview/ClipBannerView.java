package com.lyzon.ui.clipbannerview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by laoyongzhi on 2017/4/28.
 */

public class ClipBannerView extends ViewPager {

    public ClipBannerView(Context context) {
        super(context);
    }

    public ClipBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setPageMargin(16);
        this.setOffscreenPageLimit(3);
        this.setPageTransformer(false, new MyPageTransformer());
    }

    /**
     * 播放时间
     */
    private int showTime = 3000;


    /**
     * 设置播放时间，默认3秒
     *
     * @param showTimeMillis 毫秒
     */
    public void setShowTime(int showTimeMillis) {
        this.showTime = showTime;
    }

    /**
     * 开始
     */
    public void start() {
        stop();
        postDelayed(player, showTime);
    }

    /**
     * 停止
     */
    public void stop() {
        removeCallbacks(player);
    }

    /**
     * 播放上一个
     */
    public void previous() {
        play(-1);
    }

    /**
     * 播放下一个
     */
    public void next() {
        play(1);
    }

    /**
     * 执行播放
     *
     * @param i -1 is previous , 1 is next
     */
    private synchronized void play(int i) {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();
            currentItem += i;
            if (currentItem > count)
                currentItem = 0;
            setCurrentItem(currentItem);
        }
        start();
    }

    /**
     * 播放器
     */
    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play(1);
        }
    };

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE)
                    start();
                else if (state == SCROLL_STATE_DRAGGING)
                    stop();
            }
        });
    }

}
