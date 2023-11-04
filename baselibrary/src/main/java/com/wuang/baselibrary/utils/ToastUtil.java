package com.wuang.baselibrary.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
public class ToastUtil {
    private static final String TAG = ToastUtil.class.getSimpleName();
    private static volatile ToastUtil mInstance;
    private final Handler mHandler;
    private Context mAppContext;

    private ToastUtil() {
        HandlerThread mHandlerThread = new HandlerThread(TAG);
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    /**
     * 需在app的onCreate中调用一次，表示初始化。
     * @param app Application
     */
    public void init(Application app) {
        mAppContext = app;
    }

    public static ToastUtil getInstance() {
        if(mInstance == null) {
            synchronized (ToastUtil.class) {
                if(mInstance == null) {
                    mInstance = new ToastUtil();
                }
            }
        }
        return mInstance;
    }

    public void show(String info) {
        show(info, ToastUtil.LENGTH_SHORT);
    }

    public void show(String info, @Duration int duration) {
        mHandler.post(() -> Toast.makeText(mAppContext, info, duration).show());
    }

    public void show(String info, int gravity, int xOffset, int yOffset) {
        show(info, ToastUtil.LENGTH_SHORT, gravity, xOffset, yOffset);
    }

    public void show(String info, @Duration int duration, int gravity, int xOffset, int yOffset) {
        mHandler.post(() -> {
            Toast toast = Toast.makeText(mAppContext, info, duration);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
        });
    }

    @IntDef({LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {}
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;
}
