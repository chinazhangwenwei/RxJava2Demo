package com.wwzhang.rxjava2demo.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wwzhang.rxjava2demo.R;
import com.wwzhang.rxjava2demo.adapter.ListDividerItemDecoration;
import com.wwzhang.rxjava2demo.adapter.RxDemoAdapter;
import com.wwzhang.rxjava2demo.base.BaseMvpTopBarActivity;
import com.wwzhang.rxjava2demo.manager.RouterManager;
import com.wwzhang.rxjava2demo.presenter.RxDemoPresenter;
import com.wwzhang.rxjava2demo.presenter.RxDemoView;

import java.util.List;

/**
 * Created by wwzhang on 2019-07-16
 */
public class RxDemoActivity extends BaseMvpTopBarActivity<RxDemoPresenter> implements RxDemoView,
        RxDemoAdapter.OnItemClick {

    private RecyclerView recyclerView;
    private RxDemoAdapter rxDemoAdapter;


    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ListDividerItemDecoration());
        setTitle("RxJavaDemo");
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_demo_list;
    }


    @Override
    public void setDemoData(final List<String> data) {

        if (rxDemoAdapter == null) {
            rxDemoAdapter = new RxDemoAdapter(data);
            rxDemoAdapter.setOnItemClick(this);
            recyclerView.setAdapter(rxDemoAdapter);
        } else {
            rxDemoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public RxDemoPresenter getPresenter() {
        return new RxDemoPresenter();
    }

    @Override
    public void onItemClick(String content) {
        if (content.equals("just")) {
            RouterManager.startJustDemoActivity(this);
        }

    }
}
