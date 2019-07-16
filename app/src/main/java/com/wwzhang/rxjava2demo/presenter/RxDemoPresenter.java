package com.wwzhang.rxjava2demo.presenter;

import android.os.Handler;

import com.wwzhang.rxjava2demo.base.BasePresenter;

import java.util.Arrays;

/**
 * Created by wwzhang on 2019-07-16
 */
public class RxDemoPresenter extends BasePresenter<RxDemoView> {

    private String[] data = {"just", "take", "take", "skip", "filter", "compose", "zip", "map", "flatMap"};

    @Override
    public void onStart() {
        view.showLoading("loading");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setDemoData(Arrays.asList(data));
                view.hideLoading();
            }
        },1000);


    }
}
