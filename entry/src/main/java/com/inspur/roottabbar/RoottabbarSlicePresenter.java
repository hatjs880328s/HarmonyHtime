package com.inspur.roottabbar;


import com.inspur.uti.BottomTabbar;
import com.inspur.uti.TabbarItemInfo;
import com.inspur.htime.ResourceTable;
import com.inspur.provider.AbilitySliceProvider;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

public class RoottabbarSlicePresenter {

    private AbilitySliceProvider mAbilitySliceProvider;
    private List<TabbarItemInfo<?>> infoList;
    private BottomTabbar tabBottomLayout;
    private Context mContext;
    public RoottabbarSlicePresenter(AbilitySliceProvider abilitySliceProvider) {
        mAbilitySliceProvider = abilitySliceProvider;
        mContext = abilitySliceProvider.getContext();
        initBottom();
    }

    private void initBottom() {
        tabBottomLayout = (BottomTabbar) mAbilitySliceProvider.findComponentById(ResourceTable.Id_bottom_navigation_bar);
        infoList = new ArrayList<>();
        // 获取color.json文件中定义的颜色值
        int defaultColor = mAbilitySliceProvider.getColor(ResourceTable.Color_default_color);
        int tintColor = mAbilitySliceProvider.getColor(ResourceTable.Color_tint_color);
        // 获取string.json文件中定义的字符串
        String home = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_home);
        String contacts = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_contacts);
        String application = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_application);
        String mine = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_mine);
        String timeline = mAbilitySliceProvider.getString(ResourceTable.String_tabbar_timeline);

        // 首页
        TabbarItemInfo<Integer> honeInfo = new TabbarItemInfo<>(home,
                ResourceTable.Media_home_normal,
                ResourceTable.Media_home_selected,
                defaultColor, tintColor);
        // 收藏
        TabbarItemInfo<Integer> favoriteInfo = new TabbarItemInfo<>(contacts,
                ResourceTable.Media_favorite_normal,
                ResourceTable.Media_favorite_selected,
                defaultColor, tintColor);
        // 分类
        TabbarItemInfo<Integer> categoryInfo = new TabbarItemInfo<>(timeline,
                ResourceTable.Media_category_normal,
                ResourceTable.Media_category_selected,
                defaultColor, tintColor);
        // 推荐
        TabbarItemInfo<Integer> recommendInfo = new TabbarItemInfo<>(application,
                ResourceTable.Media_recommend_normal,
                ResourceTable.Media_recommend_selected,
                defaultColor, tintColor);
        // 我的
        TabbarItemInfo<Integer> profileInfo = new TabbarItemInfo<>(mine,
                ResourceTable.Media_profile_normal,
                ResourceTable.Media_profile_selected,
                defaultColor, tintColor);
        // 将每个条目的数据放入到集合
        infoList.add(honeInfo);
        infoList.add(favoriteInfo);
        infoList.add(categoryInfo);
        infoList.add(recommendInfo);
        infoList.add(profileInfo);

//        // 设置底部导航栏的透明度
        tabBottomLayout.setBarBottomAlpha(0.85f);
//        // 初始化所有的条目
        tabBottomLayout.initInfo(infoList);
//        tabBottomLayout.addBarSelectedChangeListener((index, prevInfo, nextInfo) ->
//                // 点击条目后给出提示信息
//                Toast.show(mContext, nextInfo.name));
//        // 设置默认选中的条目，该方法一定要在最后调用
//        tabBottomLayout.defaultSelected(honeInfo);
    }
}