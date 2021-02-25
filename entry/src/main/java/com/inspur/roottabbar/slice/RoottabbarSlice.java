package com.inspur.roottabbar.slice;

import com.inspur.htime.ResourceTable;
import com.inspur.provider.AbilitySliceProvider;
import com.inspur.provider.RoottabbarSlicePresenter;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

public class RoottabbarSlice extends AbilitySlice implements AbilitySliceProvider {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_roottabbar);
        new RoottabbarSlicePresenter(this);
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
    }

    /// 点击事件响应
    private void progressTabAction() {
    }
}
