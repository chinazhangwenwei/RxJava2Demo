package com.wwzhang.rxjava2demo.manager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by wwzhang on 2019-07-18
 */
public class NetManager {

    private OkHttpClient okHttpClient;


    private NetManager() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .build();
    }


    public static class NetManagerInstance {
        private static NetManager netManager = new NetManager();
    }


    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }


    public static NetManager getNetManager() {
        return NetManagerInstance.netManager;
    }


}
