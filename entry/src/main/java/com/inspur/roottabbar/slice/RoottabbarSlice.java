package com.inspur.roottabbar.slice;

import com.inspur.htime.ResourceTable;
import com.inspur.roottabbar.Roottabbar;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;

public class RoottabbarSlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_roottabbar);

        this.initUI();
        this.progressTabAction();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    /// UI初始化
    private void initUI() {
//        TabList roottabbar = (TabList) findComponentById(ResourceTable.Id_roottabbar_id);
//        if (roottabbar == null) { return; }
//
//        TabList.Tab mainPage = roottabbar.new Tab(getContext());
//        mainPage.setText("沟通");
//        roottabbar.addTab(mainPage);
//
//        TabList.Tab contactPage = roottabbar.new Tab(getContext());
//        contactPage.setText("通讯录");
//        roottabbar.addTab(contactPage);
//
//        TabList.Tab appsPage = roottabbar.new Tab(getContext());
//        appsPage.setText("应用");
//        roottabbar.addTab(appsPage);
//
//        TabList.Tab minePage = roottabbar.new Tab(getContext());
//        minePage.setText("我的");
//        roottabbar.addTab(minePage);
//
//        roottabbar.setFixedMode(true);
    }

    /// 点击事件响应
    private void progressTabAction() {
//        TabList roottabbar = (TabList) findComponentById(ResourceTable.Id_roottabbar_id);
//        if (roottabbar == null) { return; }
//        roottabbar.addTabSelectedListener(new TabList.TabSelectedListener() {
//            @Override
//            public void onSelected(TabList.Tab tab) {
//
//            }
//
//            @Override
//            public void onUnselected(TabList.Tab tab) {
//
//            }
//
//            @Override
//            public void onReselected(TabList.Tab tab) {
//
//            }
//        });
    }
}
