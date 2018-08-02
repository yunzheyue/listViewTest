package com.example.app10;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * autour : lbing
 * date : 2018/8/2 0002 09:22
 * className :
 * version : 1.0
 * description :
 */


public class MyListView extends ListView {


    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //实现回弹效果
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, 300, isTouchEvent);
    }
}
