package com.linstick.timeaxis.views;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.linstick.timeaxis.adapters.FeatureAdapter;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class CustomFeatureView extends ViewGroup {
    private static final String TAG = "CustomFeatureView";

    // 最终确定下来的子View的左上角坐标
    private int[][] childLeftTop;
    // 自身的宽高
    private int width = 0, height = 0;
    // 初始边距
    private int initialMargin;
    // 子View的大小，默认取第一个子View的宽度
    private int childSize = 0;
    // 当前处理的子View的索引号
    private int targetViewIndex;
    // 当前事件是否为点击事件
    private boolean isClick;
    // 拖动手势处理
    private ViewDragHelper dragHelper;
    // 子View适配器
    private FeatureAdapter adapter;
    // 拖拽事件回调
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        // 是否允许子View被拖动
        @Override
        public boolean tryCaptureView(View child, int pointerId) {

            int itemCount = getChildCount();
            for (int i = 0; i < itemCount; i++) {
                if (getChildAt(i) == child) {
                    targetViewIndex = i;
                    break;
                }
            }
            isClick = true;
            child.setAlpha(0.8f);
            return true;
        }

        // 垂直拖动控制，边界访问需要结合getViewVerticalDragRange确定
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if (top < 0) {
                top = 0;
            } else if (top > height - childSize) {
                top = height - childSize;
            }
            return top;
        }

        // 水平拖动控制，边界访问需要结合getViewHorizontalDragRange确定
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left < 0) {
                left = 0;
            } else if (left > width - childSize) {
                left = width - childSize;
            }
            return left;
        }

        // 位置发生改变时回调
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            isClick = false;
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        // 手势松开时回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (isClick && adapter != null) {
                adapter.onItemClick(releasedChild, targetViewIndex);
            } else {
                if (isNewPositionAvailable(releasedChild, targetViewIndex)) {
                    childLeftTop[targetViewIndex][0] = releasedChild.getLeft();
                    childLeftTop[targetViewIndex][1] = releasedChild.getTop();
                } else {
                    dragHelper.smoothSlideViewTo(releasedChild, childLeftTop[targetViewIndex][0], childLeftTop[targetViewIndex][1]);
                    invalidate();
                }

            }
            releasedChild.setAlpha(1.0f);
            super.onViewReleased(releasedChild, xvel, yvel);
        }

        // 确定水平边界
        @Override
        public int getViewHorizontalDragRange(View child) {
            return width - childSize;
        }

        // 确定垂直边界
        @Override
        public int getViewVerticalDragRange(View child) {
            return height - childSize;
        }
    };

    public CustomFeatureView(Context context) {
        this(context, null);
    }

    public CustomFeatureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFeatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dragHelper = ViewDragHelper.create(this, callback);
    }

    // 设置子View适配器，类似于ListView中的adapter
    public void setAdapter(FeatureAdapter adapter) {
        this.adapter = adapter;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 从适配器中获取子View的数据，并进行测量
        removeAllViews();
        if (adapter != null || adapter.getCount() > 0) {
            int itemCount = adapter.getCount();
            for (int i = 0; i < itemCount; i++) {
                addView(adapter.getView(i, null, this));
            }
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            childSize = getChildAt(0).getMeasuredWidth();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // 计算记录第一次启动时子View需要放置的位置
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        setFeatureIconData();
    }

    // 布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int left = childLeftTop[i][0];
            int right = childLeftTop[i][0] + childSize;
            int top = childLeftTop[i][1];
            int bottom = childLeftTop[i][1] + childSize;
            getChildAt(i).layout(left, top, right, bottom);
        }
    }

    // 拦截所有的事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    // 将自身拦截的事件交由ViewDragHelper处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!isActionDownAvailable((int) event.getX(), (int) event.getY())) {
                    // 点击在任何一个子View的圆形区域内，此事件不处理
                    return false;
                }
                break;
        }
        dragHelper.processTouchEvent(event);
        return true;

    }

    // 平滑滚动
    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(false)) {
            invalidate();
        }
    }

    // 检查当前的点击的位置是否在任意一个子View的圆形区域上
    private boolean isActionDownAvailable(int x, int y) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int radius = childSize / 2;
            int centerX = childLeftTop[i][0] + radius;
            int centerY = childLeftTop[i][1] + radius;
            if (Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2) < Math.pow(radius, 2)) {
                return true;
            }
        }
        return false;
    }

    // 检查当前View是否与其他的View的圆形区域重合
    private boolean isNewPositionAvailable(View child, int index) {
        int radius = childSize / 2;
        int centerX = child.getLeft() + radius;
        int centerY = child.getTop() + radius;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == index) {
                continue;
            }
            int x = childLeftTop[i][0] + radius;
            int y = childLeftTop[i][1] + radius;

            if (Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2) < Math.pow(childSize, 2)) {
                return false;
            }
        }
        return true;
    }

    private void setFeatureIconData() {
        int childCount = getChildCount();
        childLeftTop = new int[childCount][2];
        initialMargin = (width - childSize * 3) / 3;
        int tempMarginLeft = initialMargin / 2;
        int tempMarginTop = tempMarginLeft;
        for (int i = 0; i < childCount; i++) {
            if (i % 3 == 0) {
                tempMarginLeft = initialMargin / 2;
                tempMarginTop = tempMarginLeft + (initialMargin + childSize) * (i / 3);
            } else {
                tempMarginLeft += childSize + initialMargin;
            }
            childLeftTop[i][0] = tempMarginLeft;
            childLeftTop[i][1] = tempMarginTop;
        }
    }

    public void resetFeatureIconData() {
        setFeatureIconData();
        requestLayout();
    }
}
