package com.inspur.htime.usl.roottabbar;

import com.inspur.htime.usl.roottabbar.slice.RoottabbarSlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;

public class Roottabbar extends FractionAbility {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(RoottabbarSlice.class.getName());
    }
}
