package com.inspur.htime.uti.UI.bottomtabbar;

import com.inspur.htime.usl.roottabbar.provider.BarComponentProvider;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.agp.components.*;
import ohos.app.Context;

public class FractionBarComponent extends StackLayout {

    private BarComponentProvider mProvider;
    private int currentPosition;

    public FractionBarComponent(Context context) {
        this(context, null);
    }

    public FractionBarComponent(Context context, AttrSet attrSet) {
        this(context, attrSet, "");
    }

    public FractionBarComponent(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
    }

    /**
     * 设置适配器
     *
     * @param provider
     */
    public void setProvider(BarComponentProvider provider) {
        if (mProvider != null || provider == null) {
            return;
        }
        currentPosition = -1;
        mProvider = provider;
    }

    /**
     * 设置点前要显示的fraction
     *
     * @param position
     */
    public void setCurrentItem(int position) {
        if (position < 0 || position >= mProvider.getCount()) {
            return;
        }
        if (currentPosition != position) {
            currentPosition = position;
            mProvider.createPageInContainer(this, position);
        }
    }

    public int getCurrentItem() {
        return currentPosition;
    }

    public Fraction getCurrentFraction() {
        if (mProvider == null) {
            return null;
        }
        return mProvider.getCurrentFraction();
    }
}
