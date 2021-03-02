package com.inspur.roottabbar.slice;

import com.inspur.htime.ResourceTable;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.ability.fraction.FractionManager;
import ohos.aafwk.content.Intent;

public class BaseAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_base);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }


    public FractionManager getFractionManager() {
        Ability ability = getAbility();
        if (ability instanceof FractionAbility) {
            FractionAbility fractionAbility = (FractionAbility) ability;
            return fractionAbility.getFractionManager();
        }
        return null;
    }

    public String getString(int resId) {
        try {
            return getResourceManager().getElement(resId).getString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getColor(int colorId) {
        try {
            return getResourceManager().getElement(colorId).getColor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
