package com.inspur.provider;


import com.inspur.fraction.*;
import com.inspur.uti.BottomTabbar;
import com.inspur.uti.FractionBarComponent;
import com.inspur.uti.TabbarItemInfo;
import com.inspur.htime.ResourceTable;
import com.inspur.provider.AbilitySliceProvider;
import ohos.aafwk.ability.fraction.*;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

public class RootTabBarSlicePresenter {

    private AbilitySliceProvider mAbilitySliceProvider;
    private List<TabbarItemInfo<?>> infoList;
    private BottomTabbar tabBottomLayout;
    private Context mContext;
    private FractionBarComponent mFractionBarComponent;
    public RootTabBarSlicePresenter(AbilitySliceProvider abilitySliceProvider) {
        mAbilitySliceProvider = abilitySliceProvider;
        mContext = abilitySliceProvider.getContext();
        initBottom();
    }

    private void initBottom() {
        tabBottomLayout = (BottomTabbar) mAbilitySliceProvider.findComponentById(ResourceTable.Id_bottom_navigation_bar);
        infoList = new ArrayList<>();
        // 获取string.json文件中定义的字符串
        String home = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_home);
        String favorite = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_contacts);
        String category = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_timeline);
        String find = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_application);
        String profile = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_mine);

        int defaultColor = mAbilitySliceProvider.getColor(ResourceTable.Color_default_color);
        int tintColor = mAbilitySliceProvider.getColor(ResourceTable.Color_tint_color);
        // 首页
        TabbarItemInfo<Integer> homeInfo = new TabbarItemInfo<>(home,
                ResourceTable.Media_home_normal,
                ResourceTable.Media_home_selected,
                defaultColor, tintColor);
        homeInfo.fraction = HomeFraction.class;
        // 通讯录
        TabbarItemInfo<Integer> favoriteInfo = new TabbarItemInfo<>(favorite,
                ResourceTable.Media_favorite_normal,
                ResourceTable.Media_favorite_selected,
                defaultColor, tintColor);
        favoriteInfo.fraction = ContactsFraction.class;
        // 分类
        TabbarItemInfo<Integer> categoryInfo = new TabbarItemInfo<>(category,
                ResourceTable.Media_category_normal,
                ResourceTable.Media_category_selected,
                defaultColor, tintColor);
        categoryInfo.fraction = TimelineFraction.class;
        // 发现
        TabbarItemInfo<Integer> findInfo = new TabbarItemInfo<>(find,
                ResourceTable.Media_recommend_normal,
                ResourceTable.Media_recommend_selected,
                defaultColor, tintColor);
        findInfo.fraction = ApplicationFraction.class;
        // 我的
        TabbarItemInfo<Integer> profileInfo = new TabbarItemInfo<>(profile,
                ResourceTable.Media_profile_normal,
                ResourceTable.Media_profile_selected,
                defaultColor, tintColor);
        profileInfo.fraction = MimeFraction.class;

        // 将每个条目的数据放入到集合
        infoList.add(homeInfo);
        infoList.add(favoriteInfo);
        infoList.add(categoryInfo);
        infoList.add(findInfo);
        infoList.add(profileInfo);
        // 设置底部导航栏的透明度
        tabBottomLayout.setBarBottomAlpha(0.85f);
        // 初始化所有的条目
        tabBottomLayout.initInfo(infoList);
        initFractionBarComponent();
        tabBottomLayout.addBarSelectedChangeListener((index, prevInfo, nextInfo) ->
                // 显示fraction
                mFractionBarComponent.setCurrentItem(index));
        // 设置默认选中的条目，该方法一定要在最后调用
        tabBottomLayout.defaultSelected(homeInfo);
    }

    private void initFractionBarComponent() {
        FractionManager fractionManage = mAbilitySliceProvider.getFractionManager();
        BottomBarComponentProvider provider = new BottomBarComponentProvider(infoList, fractionManage);
        mFractionBarComponent = (FractionBarComponent) mAbilitySliceProvider.findComponentById(ResourceTable.Id_fraction_bar_component);
        mFractionBarComponent.setProvider(provider);
    }
}