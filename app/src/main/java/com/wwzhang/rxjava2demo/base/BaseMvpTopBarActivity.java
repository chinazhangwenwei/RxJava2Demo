package com.wwzhang.rxjava2demo.base;

import android.os.Bundle;

/**
 * Created by wwzhang on 2019-07-16
 */
public abstract class BaseMvpTopBarActivity<T extends BasePresenter>
        extends BaseTopBarActivity {

    private T presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        presenter.bindView(this);
        getLifecycle().addObserver(presenter);

    }

    public abstract T getPresenter();
}
