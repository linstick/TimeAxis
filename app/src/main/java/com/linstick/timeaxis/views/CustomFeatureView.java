package com.linstick.timeaxis.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class CustomFeatureView extends ViewGroup {
    private static final String TAG = "CustomFeatureView";
    private final float[][] SIZE_RATIOS = {
            {0.56f, 0.15f},
            {0.19f, 0.34f},
            {0.79f, 0.45f},
            {0.32f, 0.59f},
            {0.69f, 0.78f}
    };

    private int[][] childCoords = new int[SIZE_RATIOS.length][SIZE_RATIOS.length];

    public CustomFeatureView(Context context) {
        super(context);
    }

    public CustomFeatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFeatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int childCount = getChildCount();
        for (int i = 0; i < childCount && i < SIZE_RATIOS.length; i++) {
            // 中心点的x坐标
            childCoords[i][0] = (int) (w * SIZE_RATIOS[i][0]);
            childCoords[i][1] = (int) (h * SIZE_RATIOS[i][1]);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount && i < SIZE_RATIOS.length; i++) {
            View view = getChildAt(i);
            int halfWidth = view.getMeasuredWidth() / 2;
            int halfHeight = view.getMeasuredHeight() / 2;
            int left = childCoords[i][0] - halfWidth;
            int right = childCoords[i][0] + halfWidth;
            int top = childCoords[i][1] - halfHeight;
            int bottom = childCoords[i][1] + halfHeight;
            view.layout(left, top, right, bottom);
        }
    }

}
