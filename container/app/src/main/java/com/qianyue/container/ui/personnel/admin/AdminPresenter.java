package com.qianyue.container.ui.personnel.admin;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qianyue.container.base.BasePresenter;
import com.qianyue.container.common.ActionHelper;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.db.UserInfo;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class AdminPresenter extends BasePresenter<AdminView, AdminActivity>
        implements BaseQuickAdapter.OnItemClickListener {
    private List<UserInfo> data = new ArrayList<>();
    private AdminAdapter mAdapter;
    private String name; // 标题
    private int type; // 类型

    public AdminPresenter(AdminView view, AdminActivity activity) {
        super(view, activity);
    }

    public void initPresenter(Intent intent) {
        this.name = intent.getStringExtra("name");
        this.type = intent.getIntExtra("type", 2);
        ActionHelper.UnNullAction(mView, () -> {
            mView.titleBar(name);
            mView.btnStyle(type);
        });
    }

    public void bindVeiw(RecyclerView view) {
        mAdapter = new AdminAdapter(data);
        mAdapter.bindToRecyclerView(view);
        mAdapter.setOnItemClickListener(this);
    }

    public void getData() {
//        data.clear();
//        data.addAll(LitePal.where("type = ?", String.valueOf(type)).find(UserInfo.class));
//        mAdapter.notifyDataSetChanged();
        LitePal.where("type = ?", String.valueOf(type)).findAsync(UserInfo.class)
                .listen(this::onFinish);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        UIhelper.gotoPersonnelInfoActivity(mAcitity, (UserInfo) adapter.getData().get(position), name, type);
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    private void onFinish(List<UserInfo> list) {
        DiffUtil.DiffResult _diff = DiffUtil.calculateDiff(new AdminAdapterDiff(mAdapter.getData(), list));
        mAdapter.setData(list);
        _diff.dispatchUpdatesTo(mAdapter);
    }
}
