package com.wwzhang.rxjava2demo.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


/**
 * Created by wwzhang on 2019-07-16
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ProgressDialog progressDialog;


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


}
