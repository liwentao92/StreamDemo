package com.mifly.streamdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.mifly.streamdemo.view.ErrorView;

/**
 * Created by lwt on 2016/9/21.
 */

public class MyWindowManager {

    private static ErrorView mErrorView;
    /**
     * 火箭发射台的参数
     */
    public static WindowManager.LayoutParams launcherParams = null;
    /**
     * 用于控制在屏幕上添加或移除悬浮窗
     */
    public static WindowManager mWindowManager;

    public static FloatPlayerUI mFloatPlayerUI;

    public static WindowManager.LayoutParams wmParams = null;

    public static void initWindowFloatView(Context context) {
        //屏幕宽度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                context.getResources().getDimensionPixelSize(R.dimen.float_window_root_width),
                context.getResources().getDimensionPixelSize(R.dimen.float_window_root_height));

        if (mFloatPlayerUI == null){
            mFloatPlayerUI = new FloatPlayerUI(context);
            mFloatPlayerUI.setLayoutParams(params);
            mFloatPlayerUI.setBackgroundColor(Color.BLACK);
            mFloatPlayerUI.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            if (wmParams == null){
                wmParams = new WindowManager.LayoutParams();
                mWindowManager = getWindowManager(context);
                //设置TYPE_PHONE，需要申请android.permission.SYSTEM_ALERT_WINDOW权限
                //TYPE_TOAST同样可以实现悬浮窗效果，不需要申请其他权限
                wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                wmParams.format = PixelFormat.RGBA_8888;
                wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
                wmParams.gravity = Gravity.LEFT | Gravity.TOP;
                int width = context.getResources().getDisplayMetrics().widthPixels;
                int height = context.getResources().getDisplayMetrics().heightPixels;
                wmParams.verticalWeight = 0;
                wmParams.horizontalWeight = 0;
                wmParams.width = context.getResources().getDimensionPixelSize(R.dimen.float_window_root_width);
                wmParams.height = context.getResources().getDimensionPixelSize(R.dimen.float_window_root_height);
                //wmParams.x = (width - wmParams.width) / 2;
                //wmParams.y = (height - wmParams.height) / 2;
                wmParams.x = 16;
                wmParams.y = 160;
                wmParams.alpha = 1.0f;
                wmParams.token = mFloatPlayerUI.getApplicationWindowToken();
                mWindowManager.addView(mFloatPlayerUI, wmParams);
            }
        }

    }



    /**
     * 创建一个窗口，位置为屏幕底部。
     */
    public static void createLauncher(Context context) {
        WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if (mErrorView == null) {
            mErrorView = new ErrorView(context);
            if (launcherParams == null) {
                launcherParams = new WindowManager.LayoutParams();
                launcherParams.x = screenWidth / 2 - ErrorView.width / 2;
                launcherParams.y = screenHeight - ErrorView.height;
                launcherParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                launcherParams.format = PixelFormat.RGBA_8888;
                launcherParams.gravity = Gravity.LEFT | Gravity.TOP;
                launcherParams.width = ErrorView.width;
                launcherParams.height = ErrorView.height;
            }
            windowManager.addView(mErrorView, launcherParams);
        }
    }

    /**
     * 如果WindowManager还未创建，则创建一个新的WindowManager返回。否则返回当前已创建的WindowManager。
     *
     * @param context
     *            必须为应用程序的Context.
     * @return WindowManager的实例，用于控制在屏幕上添加或移除悬浮窗。
     */
    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    /**
     * 将火箭发射台从屏幕上移除。
     */
    public static void removeLauncher(Context context) {
        if (mErrorView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mErrorView);
            mErrorView = null;
        }
    }

    /**
     * 更新火箭发射台的显示状态。
     */
    public static void updateLauncher() {
        if (mErrorView != null) {
            mErrorView.updateLauncherStatus(isReadyToLaunch());
        }
    }

    /**
     * 判断小火箭是否准备好发射了。
     *
     * @return 当火箭被发到发射台上返回true，否则返回false。
     */
    public static boolean isReadyToLaunch() {

        if ((wmParams.y + wmParams.height > launcherParams.y)) {
            return true;
        }
        return false;
    }

    /**
     * 将小悬浮窗从屏幕上移除。
     */
    public static void removeSmallWindow(Context context) {
        if (mFloatPlayerUI != null) {

            if(mFloatPlayerUI.isAttachedToWindow()){
                mWindowManager.removeView(mFloatPlayerUI);
                mFloatPlayerUI = null;
                wmParams = null;
            }
        }
    }

}
