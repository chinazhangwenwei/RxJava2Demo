package com.wwzhang.rxjava2demo.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wwzhang.rxjava2demo.R;

/**
 * Created by wwzhang on 2019-07-16
 */
public abstract class BaseTopBarActivity extends BaseActivity {

    private Toolbar toolBar;
    private FrameLayout viewContent;
    private String menuStr;
    private int menuId;

    private OnTitleClick onTitleClickLeft;
    private OnTitleClick onTitleClickRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_title_layout);
        toolBar = findViewById(R.id.toolbar);
        viewContent = findViewById(R.id.content_container);
        setSupportActionBar(toolBar);
        LayoutInflater.from(this).inflate(getLayoutId(), viewContent);
        initView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (onTitleClickRight != null) {
                onTitleClickRight.click();
            }

        } else if (item.getItemId() == R.id.menu_1) {
            if (onTitleClickLeft != null) {
                onTitleClickLeft.click();
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuId);
        }
        if (menuStr != null) {
            menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuStr != null || menuId != 0)
            getMenuInflater().inflate(R.menu.base_title_menu, menu);

        return true;
    }

    protected void setTopLeft(int iconId,OnTitleClick onTitleClick){
        toolBar.setNavigationIcon(iconId);
        this.onTitleClickLeft=onTitleClick;
    }

    protected void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            ((TextView)toolBar.findViewById(R.id.tv_title)).setText(title);
        }
    }

    protected void setTopRight(String menuStr,int menuId,OnTitleClick onTitleClick){
        this.menuId = menuId;
        setTopRight(menuStr,onTitleClick);
    }


    protected void setTopRight(String menuStr,OnTitleClick onTitleClick){
        this.menuStr =menuStr;
        this.onTitleClickRight = onTitleClick;
    }


    interface OnTitleClick {

        void click();
    }
}
