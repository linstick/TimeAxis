package com.linstick.timeaxis.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class FeatureView extends LinearLayout {
    private static final String TAG = "FeatureView";

    private int halfWidth;
    private int halfHeight;
    private int centerX;
    private int centerY;

    public FeatureView(Context context) {
        super(context);
    }

    public FeatureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FeatureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        halfWidth = w / 2;
        halfHeight = h / 2;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onTouchEvent: 来过");
        boolean isResume = false;
        int currX = (int) ev.getX();
        int currY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // do nothing
                break;
            case MotionEvent.ACTION_MOVE:
                isResume = true;
                centerX = currX;
                centerY = currY;

                layout(centerX - halfWidth, centerY - halfHeight, centerX + halfWidth, centerY + halfHeight);
                break;

            case MotionEvent.ACTION_UP:
                // do nothing
                break;
        }
        return true;
    }
}
