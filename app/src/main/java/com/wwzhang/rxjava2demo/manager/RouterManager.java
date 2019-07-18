package com.wwzhang.rxjava2demo.manager;

import android.content.Context;
import android.content.Intent;

import com.wwzhang.rxjava2demo.view.RxJustDemoActivity;

/**
 * Created by wwzhang on 2019-07-17
 */
public class RouterManager {

    public static void startJustDemoActivity(Context context) {
        Intent intent = new Intent(context, RxJustDemoActivity.class);
        context.startActivity(intent);
    }

}
