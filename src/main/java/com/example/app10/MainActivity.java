package com.example.app10;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyListView listview;
    private Toolbar toolbar;
    private ObjectAnimator objectAnimator;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listview = (MyListView) findViewById(R.id.listview);

        ArrayList<String> list = new ArrayList<>();
        list.add("测试1");
        list.add("测试2");
        list.add("测试3");
        list.add("测试4");
        list.add("测试5");
        list.add("测试6");
        list.add("测试7");
        list.add("测试8");
        list.add("测试9");
        list.add("测试10");
        list.add("测试11");
        list.add("测试12");
        list.add("测试13");
        list.add("测试14");
        list.add("测试15");
        list.add("测试16");
        list.add("测试17");
        list.add("测试18");
        list.add("测试19");
        list.add("测试20");
        //添加无数据view
        View emptyView = LayoutInflater.from(this).inflate(R.layout.no_data, null);
        emptyView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));//设置LayoutParams
        ((ViewGroup) listview.getParent()).addView(emptyView);//添加到当前的View hierarchy
        listview.setEmptyView(emptyView);


        //listView滚动的时候的toolbar的显示和隐藏
        View headerView = new View(this);
        headerView.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material  //竟然不提示
                )));
        listview.addHeaderView(headerView);


        listview.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_item, R.id.tv_test, list));


        ViewConfiguration.get(this).getScaledTouchSlop();


        listview.setOnTouchListener(new View.OnTouchListener() {
            float lastY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float curY = event.getY();
                        //向上滑 隐藏toolbar
                        if ((curY < lastY)) {
                            Log.e("TAG", "向上滑");
                            toolbarAnimator(0);

                        } else {
                            Log.e("TAG", "向下滑");
                            toolbarAnimator(1);
                        }
                        lastY = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });


        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean showBottomToa = false;
            private boolean showTopToa = false;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("TAG", "onScrollStateChanged===view===" + view.getClass() +
                        "    scrollState===" + scrollState);
//                public static int SCROLL_STATE_IDLE = 0;
//                public static int SCROLL_STATE_TOUCH_SCROLL = 1;
//                public static int SCROLL_STATE_FLING = 2;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e("TAG", "onScroll===view===" + view.getClass() +
                        "    firstVisibleItem===" + firstVisibleItem + "    visibleItemCount===" + visibleItemCount + "    totalItemCount==" + totalItemCount);
//                判断滑动到最后一行
                if (firstVisibleItem + visibleItemCount == totalItemCount && !showBottomToa) {
                    showBottomToa = true;
                    Toast.makeText(MainActivity.this, "滑动到底部了", Toast.LENGTH_SHORT).show();
                }
                if (firstVisibleItem + visibleItemCount != totalItemCount) {
                    showBottomToa = false;
                }
                if (firstVisibleItem == 0 && showTopToa) {
                    showTopToa = false;
                    Toast.makeText(MainActivity.this, "滑动到顶部", Toast.LENGTH_SHORT).show();
                }
                if (firstVisibleItem != 0) {
                    showTopToa = true;
                }
            }
        });
    }

    private void toolbarAnimator(int direction) {

        if (objectAnimator != null && objectAnimator.isRunning()) {
            objectAnimator.cancel();
        }
        Log.e("TAG", "toolbar.getTranslationY()====" + toolbar.getTranslationY());
        //向上滑动
        if (0 == direction) {
            objectAnimator = ObjectAnimator.ofFloat(toolbar, "translationY", toolbar.getTranslationY(), -toolbar.getMeasuredHeight());
            //向下滑动
        } else if (1 == direction) {
            objectAnimator = ObjectAnimator.ofFloat(toolbar, "translationY", toolbar.getTranslationY(), 0);
        }
        objectAnimator.start();
    }

    public void click(View view) {
        listview.smoothScrollByOffset(300);
    }

    public void click1(View view) {
//        listview.setSelection(3);
        listview.smoothScrollToPosition(5);
        //这个仅仅是获取的当前屏幕上显示的item的数量
        int childCount = listview.getChildCount();
        Log.e("TAG", "childCount===" + childCount);
        for (int i = 0; i < childCount; i++) {
            View child = listview.getChildAt(i);
            if (i == 3) {
                child.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        }
    }

    public void click2(View view) {
        int firstVisiblePosition = listview.getFirstVisiblePosition();

        int lastVisiblePosition = listview.getLastVisiblePosition();
        Log.e("TAG", "firstVisiblePosition===" + firstVisiblePosition + "   lastVisiblePosition==" + lastVisiblePosition);

    }
}
