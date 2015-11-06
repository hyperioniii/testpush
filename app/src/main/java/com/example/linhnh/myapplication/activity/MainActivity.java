package com.example.linhnh.myapplication.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
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
import com.example.linhnh.myapplication.fragment.FragmentEditImage;
import com.example.linhnh.myapplication.fragment.FragmentListImage;
import com.example.linhnh.myapplication.model.CalenderEvent;
import com.example.linhnh.myapplication.util.CustomsRecycleViewHoriziontal;
import com.example.linhnh.myapplication.util.DebugLog;
import com.example.linhnh.myapplication.util.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {



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
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                popEntireFragmentBackStack();
                FragmentUtil.replaceFragment(MainActivity.this, FragmentEditImage.intantce(),null);
            }
        });

        fab.setRippleColor(getResources().getColor(R.color.colorPrimary));
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
    }

    @Override
    public void initData() {
            FragmentUtil.showFragment(MainActivity.this, FragmentListImage.intantce(), false, null, "", false);
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(MainScreenSettingEvent event) {

    }

    private void popEntireFragmentBackStack() {
        final FragmentManager fm = getSupportFragmentManager();
        final int backStackCount = fm.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            fm.popBackStack();
        }
//        showHideLeftIcon(null);
    }

}
