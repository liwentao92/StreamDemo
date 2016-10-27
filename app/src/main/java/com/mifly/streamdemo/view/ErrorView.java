package com.mifly.streamdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mifly.streamdemo.R;

/**
 * Created by lwt on 2016/9/21.
 */

public class ErrorView extends LinearLayout {


    public static int width;

    public static int height;

    private ImageView errorImg;

    public ErrorView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.error_layout, this);
        errorImg = (ImageView) findViewById(R.id.error_img);
        width = errorImg.getLayoutParams().width;
        height = errorImg.getLayoutParams().height;
    }

    /**
     * 更新火箭发射台的显示状态。如果小火箭被拖到火箭发射台上，就显示发射。
     */
    public void updateLauncherStatus(boolean isReadyToLaunch) {
        if (isReadyToLaunch) {
            errorImg.setBackgroundResource(R.color.colorPrimaryDark);
        } else {
            errorImg.setBackgroundResource(R.color.colorAccent);
        }
    }

}
