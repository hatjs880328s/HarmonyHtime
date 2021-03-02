package com.inspur.htime.uti.UI.fraction;

import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;

public abstract class BaseFraction extends Fraction {
    protected Component mComponent;

    public abstract int getUIContent();

    public abstract void initUIContent();

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        mComponent = scatter.parse(getUIContent(), container, false);
        initUIContent();
        return mComponent;
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
    }

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public String getString(int resId) {
        try {
            return getFractionAbility().getResourceManager()
                    .getElement(resId)
                    .getString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public int getColor(int resId) {
        try {
            return getFractionAbility().getResourceManager().getElement(resId).getColor();
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
