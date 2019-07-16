package com.wwzhang.rxjava2demo.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;



/**
 * Created by wwzhang on 2019-07-16
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ProgressDialog progressDialog;
    public Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }

    @Override
    public void showLoading(String text) {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setTitle(text);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

    }


    protected abstract int getLayoutId();

    protected abstract void initView();


    public void hideLoading() {

        progressDialog.dismiss();
        progressDialog = null;

    }

    public void showToast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
