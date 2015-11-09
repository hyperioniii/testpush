package com.example.linhnh.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.linhnh.myapplication.R;
import com.example.linhnh.myapplication.activity.MainActivity;
import com.example.linhnh.myapplication.callback.OnHeaderIconClickListener;
import com.example.linhnh.myapplication.constant.HeaderIconOption;
import com.example.linhnh.myapplication.eventbus.MainScreenSettingEvent;
import com.example.linhnh.myapplication.util.DebugLog;
import com.larswerkman.lobsterpicker.adapters.BitmapColorAdapter;
import com.larswerkman.lobsterpicker.sliders.LobsterOpacitySlider;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by LinhNguyen on 11/6/2015.
 * https://android-arsenal.com/details/1/2683
 * https://github.com/okoteogl/Photogeneia
 * https://github.com/AmineChikhaoui/PhotoEdit
 * https://github.com/heyjii/PhotoFrames
 * https://github.com/retryu/PhotoEditorView
 * https://github.com/huydx/vlcamera
 * https://github.com/AndrewShidel/Green-Screen-Photography
 */
public class FragmentEditImage extends BaseFragment {

    @InjectView(R.id.opacityslider)
    LobsterOpacitySlider opacitySlider;

    @InjectView(R.id.shadeslider)
    LobsterShadeSlider shadeSlider;

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
        MainScreenSettingEvent mainScreenSettingEvent = new MainScreenSettingEvent("My title ", HeaderIconOption.RIGHT_NONE, HeaderIconOption.LEFT_BACK);
        EventBus.getDefault().post(mainScreenSettingEvent);

        shadeSlider.addDecorator(opacitySlider);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {
        shadeSlider.setColorAdapter(new BitmapColorAdapter(getActivity(), R.drawable.default_shader_pallete));
    }

}
