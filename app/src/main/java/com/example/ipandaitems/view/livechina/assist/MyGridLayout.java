package com.example.ipandaitems.view.livechina.assist;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.ipandaitems.R;

import java.util.List;

/**
 * Created by 张豫耀 on 2017/8/28.
 */

public class MyGridLayout extends GridLayout implements View.OnLongClickListener,
        View.OnDragListener {

    // 在类中new对象的时候调用这个方法
    public MyGridLayout(Context context) {
        this(context, null);
    }

    // 这个xml布局中声明该控件的时候调用这个方法
    public MyGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 在xml布局使用样式的时候会调用这个方法
    public MyGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setColumnCount(3);
        setLayoutTransition(new LayoutTransition());
    }

    //定义一个接口对象listerner
    private OnItemSelectListener listener;

    //获得接口对象的方法。
    public void setOnItemSelectListener(OnItemSelectListener listener) {
        this.listener = listener;
    }

    //定义一个接口
    public interface OnItemSelectListener {
        public void onItemSelect(String indexString);
    }


    /**
     * 添加条目对应的字符串
     */
    public void addItems(List<String> list) {
        for (String strtv : list) {
            addTvItem(strtv);
        }
    }


    private int mMargin = 10;

    // 能否被拖拽的标记
    private boolean mIsDragedAble;

    // 被拖拽的View
    private View mDragView;

    // 设置GridLayout的条目是否可拖拽
    public void setGridLayoutItemDrageAble(boolean isDrageAble) {
        this.mIsDragedAble = isDrageAble;
    }

//    private int mIndex=0;

    public void addTvItem(String strTv) {
        final TextView tv = new TextView(getContext());
        tv.setText(strTv);

        // 设置GridLayout中条目的宽高
        LayoutParams params = new LayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels / 3
                - mMargin * 2;
        params.height = LayoutParams.WRAP_CONTENT;
        params.setMargins(mMargin, 8, mMargin, 8);
        tv.setLayoutParams(params);

        // 设置条目的背景和条目的内边距
        tv.setBackgroundResource(R.drawable.drag_item_selector);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(10, 10, 10, 10);


//        tv.setEnabled(false);
        tv.setTextColor(getResources().getColor(R.color.black));
        addView(tv);
        if (mIsDragedAble) {
            // 设置条目长按事件
            tv.setOnLongClickListener(this);
        } else
            tv.setOnLongClickListener(null);

        // 设置条目的拖拽换位
        this.setOnDragListener(this);
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(v);
                if (listener != null) {
//                    removeAllViews();
                    listener.onItemSelect(tv.getText() + "");
                }
            }
        });

//        Log.i("TAG", mIndex + "==================");
//        mIndex++;
    }

    @Override
    public boolean onDrag(View arg0, DragEvent event) {
        // String dragEvent = getDragEvent(arg1.getAction());
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                initRects();
                break;

            // 实时监听拖拽的点是否进入到某一个条目范围内
            case DragEvent.ACTION_DRAG_LOCATION:
                int i = getTouchIndex(event);
                if (i >= 0 && mDragView != null && getChildAt(i) != mDragView) {
                    removeView(mDragView);
                    addView(mDragView, i);
                }
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                if (mDragView != null)
                    mDragView.setEnabled(true);
                break;
        }

        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        mDragView = v;
        // 开始拖拽
        v.startDrag(null, new DragShadowBuilder(v), null, 0);
        v.setEnabled(false);
//        tv.setTextColor(getResources().getColor(R.color.colorBai));

        return false;
    }

    /**
     * 获取到拖拽到的那个条目对应的索引值
     *
     * @param event
     * @return
     */
    private int getTouchIndex(DragEvent event) {
        float dragX = event.getX();
        float dragY = event.getY();
        for (int i = 0; i < mRects.length; i++) {
            if (mRects[i].contains((int) dragX, (int) dragY)) {
                return i;
            }
        }
        return -1;
    }

    private Rect[] mRects;

    // 将每一个条目都封装成他们所对应的矩形并把这些矩形装进矩形数组里面
    private void initRects() {
        mRects = new Rect[getChildCount()];
        for (int i = 0; i < mRects.length; i++) {
            View childView = getChildAt(i);
            Rect rect = new Rect(childView.getLeft(), childView.getTop(),
                    childView.getRight(), childView.getBottom());
            mRects[i] = rect;
        }
    }

}
