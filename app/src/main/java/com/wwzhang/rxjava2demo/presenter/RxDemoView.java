package com.wwzhang.rxjava2demo.presenter;

import com.wwzhang.rxjava2demo.base.BaseView;

import java.util.List;

/**
 * Created by wwzhang on 2019-07-16
 */
public interface RxDemoView extends BaseView {

    void setDemoData(List<String> data);
}
