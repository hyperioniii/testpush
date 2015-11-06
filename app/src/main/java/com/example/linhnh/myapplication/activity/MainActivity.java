package com.example.linhnh.myapplication.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.linhnh.myapplication.R;
import com.example.linhnh.myapplication.adapter.DividerItemDecoration;
import com.example.linhnh.myapplication.adapter.ListEventAdapter;
import com.example.linhnh.myapplication.eventbus.MainScreenSettingEvent;
import com.example.linhnh.myapplication.model.CalenderEvent;
import com.example.linhnh.myapplication.util.CustomsRecycleViewHoriziontal;
import com.example.linhnh.myapplication.util.DebugLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.list_event)
    CustomsRecycleViewHoriziontal mRecyclerView;

    List<CalenderEvent> data = new ArrayList<>();

    @InjectView(R.id.swipe_refresh_layout_list_event)
    SwipeRefreshLayout mSwipeRefreshLayout;

    ListEventAdapter adapter;


    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, null));
        mSwipeRefreshLayout.setRefreshing(false);
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        };
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(r, 2000);
            }
        });
        fab.setRippleColor(getResources().getColor(R.color.colorPrimary));
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
    }

    @Override
    public void initData() {

setUi();
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(MainScreenSettingEvent event) {

    }

    public void setUi() {
        getdata();
        adapter = new ListEventAdapter(data) {
            @Override
            public void dataSender(int truyencaikhigithitruyen) {

            }
        };
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setOnScrollListener(new CustomsRecycleViewHoriziontal.OnScrollListener() {
            @Override
            public void onScrollChanged(CustomsRecycleViewHoriziontal scrollView, int x, int y, int oldX, int oldY) {
                DebugLog.d("============4============" + x + "++" + y);
                DebugLog.d("============5============" + oldX + "++" + oldY);

                // thằng này bắt scroll list view
                adapter.swipeView(x,oldX);
            }

            @Override
            public void onEndScroll(CustomsRecycleViewHoriziontal scrollView) {

            }
        });

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int heightSc = displaymetrics.heightPixels;
        int widthSc = displaymetrics.widthPixels;
    }

    public void getdata() {
        for (int i = 0; i < 10; i++) {
            data.add(new CalenderEvent("", "http://res.vtc.vn/media/vtcnews/2014/epi/viettu/2014_03_11/Tu%20Anh/duong-tu-anh-13_MHSK.jpg?width=200abcdefffffffffff"));
        }
    }
}
