package com.wwzhang.rxjava2demo.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by wwzhang on 2019-07-16
 */
public abstract class BasePresenter<T extends BaseView> implements LifecycleObserver {

    private CompositeDisposable compositeDisposable;
    protected T view;


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(@NonNull LifecycleOwner owner) {
        compositeDisposable = new CompositeDisposable();
        onStart();

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(@NonNull LifecycleOwner owner) {
        view = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void bindView(T view) {
        this.view = view;
    }

    public void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    //开启数据加载
    public abstract void onStart();


}
