package com.wwzhang.rxjava2demo.util;

import android.util.Log;

import com.wwzhang.rxjava2demo.BuildConfig;

/**
 * Created by wwzhang on 2019-07-17
 */
public class LogUtils {

    private static final String DEFAULT_TAG = "wwzhang";

    public static void logd(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content);
        }
    }

    public static void logi(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content);
        }
    }

    public static void logd(String content) {
        logd(DEFAULT_TAG, content);
    }
}
