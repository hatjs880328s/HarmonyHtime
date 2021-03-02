package com.inspur.roottabbar;

import com.inspur.provider.AbilitySliceProvider;
import com.inspur.roottabbar.slice.RoottabbarSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;

public class Roottabbar extends FractionAbility {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(RoottabbarSlice.class.getName());
    }
}
