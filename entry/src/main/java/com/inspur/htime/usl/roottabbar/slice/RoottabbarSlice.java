package com.inspur.htime.usl.roottabbar.slice;

import com.inspur.htime.ResourceTable;
import com.inspur.htime.usl.roottabbar.provider.AbilitySliceProvider;
import com.inspur.htime.usl.roottabbar.provider.RootTabBarSlicePresenter;
import ohos.aafwk.content.Intent;

public class RoottabbarSlice extends BaseAbilitySlice implements AbilitySliceProvider {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_roottabbar);

        new RootTabBarSlicePresenter(this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
