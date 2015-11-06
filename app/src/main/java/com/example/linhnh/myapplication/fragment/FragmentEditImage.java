package com.example.linhnh.myapplication.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.linhnh.myapplication.R;

/**
 * Created by LinhNguyen on 11/6/2015.
 * https://android-arsenal.com/details/1/2683
 */
public class FragmentEditImage extends BaseFragment {

    public static FragmentEditImage intantce() {
        FragmentEditImage fm = new FragmentEditImage();
        return fm;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_edit_image;
    }

    @Override
    protected void initView(View root) {

    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }
}
