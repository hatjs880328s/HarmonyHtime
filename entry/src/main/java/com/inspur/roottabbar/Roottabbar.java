package com.inspur.roottabbar;

import com.inspur.roottabbar.slice.RoottabbarSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class Roottabbar extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(RoottabbarSlice.class.getName());
    }
}
