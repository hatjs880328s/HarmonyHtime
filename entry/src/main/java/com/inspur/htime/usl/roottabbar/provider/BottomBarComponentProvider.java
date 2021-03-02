package com.inspur.htime.usl.roottabbar.provider;

import com.inspur.htime.uti.UI.bottomtabbar.TabbarItemInfo;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.ability.fraction.FractionManager;

import java.util.List;

public class BottomBarComponentProvider extends  BarComponentProvider {

    private List<TabbarItemInfo<?>> mInfoList;

    @Override
    public Fraction getPage(int position) {
        try {
            return mInfoList.get(position).fraction.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Fraction getFraction(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return mInfoList.size();
    }

    public BottomBarComponentProvider(List<TabbarItemInfo<?>> infoList, FractionManager fractionManager) {
        super(fractionManager);
        mInfoList = infoList;
    }
}