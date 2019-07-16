package com.wwzhang.rxjava2demo.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wwzhang.rxjava2demo.R;
import com.wwzhang.rxjava2demo.adapter.RxDemoAdapter;
import com.wwzhang.rxjava2demo.base.BaseMvpTopBarActivity;
import com.wwzhang.rxjava2demo.presenter.RxDemoPresenter;
import com.wwzhang.rxjava2demo.presenter.RxDemoView;

import java.util.List;

/**
 * Created by wwzhang on 2019-07-16
 */
public class RxDemoActivity extends BaseMvpTopBarActivity<RxDemoPresenter> implements RxDemoView {

    private RecyclerView recyclerView;
    private RxDemoAdapter rxDemoAdapter;


    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_demo_list;
    }


    @Override
    public void setDemoData(List<String> data) {
        if (rxDemoAdapter == null) {
            rxDemoAdapter = new RxDemoAdapter(data);
            recyclerView.setAdapter(rxDemoAdapter);
        } else {
            rxDemoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public RxDemoPresenter getPresenter() {
        return new RxDemoPresenter();
    }
}
