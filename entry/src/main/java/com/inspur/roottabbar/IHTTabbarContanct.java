package com.inspur.roottabbar;

import com.inspur.roottabbar.slice.IHTTabbarContanctSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class IHTTabbarContanct extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(IHTTabbarContanctSlice.class.getName());
    }
}
