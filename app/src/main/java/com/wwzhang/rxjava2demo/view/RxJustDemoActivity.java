package com.wwzhang.rxjava2demo.view;

import android.widget.TextView;

import com.wwzhang.rxjava2demo.R;
import com.wwzhang.rxjava2demo.base.BaseTopBarActivity;
import com.wwzhang.rxjava2demo.manager.NetManager;
import com.wwzhang.rxjava2demo.util.LogUtils;
import com.wwzhang.rxjava2demo.util.RxJavaUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wwzhang on 2019-07-17
 */
public class RxJustDemoActivity extends BaseTopBarActivity {

    private TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_just_demo;
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.tv_content);
        setTitle("RxJust");
        rxObservable();
        rxOkhttpTest();
        demo2();
    }


    public void demo2() {

        Flowable
                .create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                        System.out.println("发射----> 1");
                        e.onNext(1);
                        System.out.println("发射----> 2");
                        e.onNext(2);
                        System.out.println("发射----> 3");
                        e.onNext(3);
                        System.out.println("发射----> 完成");
                        e.onComplete();
                    }
                }, BackpressureStrategy.ERROR) //create方法中多了一个BackpressureStrategy类型的参数
                .subscribeOn(Schedulers.newThread())//为上下游分别指定各自的线程
                .observeOn(Schedulers.newThread())
                .subscribeWith(new ResourceSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {   //onSubscribe回调的参数不是Disposable而是Subscription
////                        s.request(Long.MAX_VALUE);            //注意此处，暂时先这么设置
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("接收----> " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("接收----> 完成");
//                    }
//                });
    }


    private void rxOkhttpTest() {

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext("nihao");
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .compose(RxJavaUtils.flowableToMain())
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(String o) {
                        LogUtils.logd(o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.logd("onErr" + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.logd("Thread:" + Thread.currentThread().getName());
                        LogUtils.logd("onComplete");
                    }
                });

        Flowable.create((FlowableEmitter<String> emitter) -> {
            Request request = new Request.Builder()
                    .url("http://gank.io/api/today1")
                    .get()
                    .build();
            Call call = NetManager.getNetManager().getOkHttpClient().newCall(request);
            Response response = call.execute();
            String content = response.body().string();
            emitter.onNext(content);
            LogUtils.logd("Thread:" + Thread.currentThread().getName());
            emitter.onComplete();

        }, BackpressureStrategy.BUFFER)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.logd(s);
                        textView.setText(s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.logd(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.logd("Thread:" + Thread.currentThread().getName());
                        LogUtils.logd("onComplete");
                    }
                });


    }


    private void rxObservable() {

        Observable.create((ObservableEmitter<Integer> emitter) -> {
                    LogUtils.logd("Thread_name:" + Thread.currentThread().getName());
                    LogUtils.logd("Observable emit 1");
                    emitter.onNext(1);
                    LogUtils.logd("Observable emit 2");
                    emitter.onNext(2);
                    LogUtils.logd("Observable emit 3");
                    emitter.onNext(3);
                    emitter.onComplete();
                    LogUtils.logd("Observable emit 4");
                    emitter.onNext(4);
                }
        ).compose(RxJavaUtils.observableToMain())
                .subscribe(new Observer<Integer>() {

                    private int i;
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.logd("onNext" + integer);
                        i++;
                        if (i == 2) {
                            LogUtils.logd("Thread_name:" + Thread.currentThread().getName());
                            disposable.dispose();
                            LogUtils.logd("onNext" + disposable.isDisposed());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.logd("OnError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.logd("onComplete");

                    }
                });

        Observable.interval(3, TimeUnit.SECONDS).
                compose(RxJavaUtils.<Long>observableToMain()).
                subscribe();
    }

    private void rxJust1() {
//        Flowable.just(1, 2, 3, 4, 5).compose(RxJavaUtils.<Integer>flowableToMain())
//                .subscribeWith(
//                        new ResourceSubscriber<Integer>() {
//
//                            @Override
//                            public void onNext(Integer integer) {
//                                textView.setText(integer + "");
//
//                            }
//
//                            @Override
//                            public void onError(Throwable t) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//
//                            }
//                        });

    }
}
