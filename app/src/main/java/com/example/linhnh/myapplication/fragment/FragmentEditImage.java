package com.example.linhnh.myapplication.fragment;

import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.linhnh.myapplication.R;
import com.example.linhnh.myapplication.callback.OnHeaderIconClickListener;
import com.example.linhnh.myapplication.constant.HeaderIconOption;
import com.example.linhnh.myapplication.eventbus.MainScreenSettingEvent;
import com.example.linhnh.myapplication.util.DebugLog;
import com.larswerkman.lobsterpicker.ColorAdapter;
import com.larswerkman.lobsterpicker.ColorDecorator;
import com.larswerkman.lobsterpicker.LobsterPicker;
import com.larswerkman.lobsterpicker.OnColorListener;
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
public class FragmentEditImage extends BaseFragment implements OnHeaderIconClickListener {

    @InjectView(R.id.opacityslider)
    LobsterOpacitySlider opacitySlider;

    @InjectView(R.id.shadeslider)
    LobsterShadeSlider shadeSlider;

    @InjectView(R.id.img_edit)
    ImageView editImg;

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

        MainScreenSettingEvent mainScreenSettingEvent = new MainScreenSettingEvent("My title ", HeaderIconOption.RIGHT_CLOSE, HeaderIconOption.LEFT_BACK);
        EventBus.getDefault().post(mainScreenSettingEvent);

        shadeSlider.addDecorator(opacitySlider);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {
        shadeSlider.setColorAdapter(new BitmapColorAdapter(getActivity(), R.drawable.default_shader_pallete));
        setColor();
    }

    Canvas canvas;
    Paint paint = new Paint();
    Drawable myIcon;
    Bitmap src, bm1;

    public void setColor() {

        Glide.with(getActivity()).load(R.drawable.images).into(editImg);
        opacitySlider.setOpacity(0);
        myIcon = getResources().getDrawable(R.drawable.images);
        src = ((BitmapDrawable) myIcon).getBitmap();
        bm1 = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bm1);


        shadeSlider.addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(int color) {
                DebugLog.d("====color=====" + color);

                if (color != 0) {
                    paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.OVERLAY));
                    canvas.drawBitmap(src, 0, 0, paint);
                    editImg.setImageBitmap(bm1);
                }
            }

            @Override
            public void onColorSelected(int color) {

            }
        });


    }

    @Override
    public void onHeaderBack() {

    }

    @Override
    public void onHeaderClose() {

    }

    @Override
    public void onHeaderSetting() {

    }

    @Override
    public void onHeaderEdit() {

    }

    @Override
    public void onHeaderDelete() {

    }
}
