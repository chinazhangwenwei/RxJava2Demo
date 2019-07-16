package com.wwzhang.rxjava2demo.base;

/**
 * Created by wwzhang on 2019-07-16
 */
public interface BaseView {

    void showLoading(String text);

    void hideLoading();

    void showToast(String txt);
}
