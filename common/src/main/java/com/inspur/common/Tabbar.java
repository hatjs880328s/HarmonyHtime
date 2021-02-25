package com.inspur.common;

import com.inspur.common.uti.TextUtils;
import ohos.agp.components.*;
import ohos.agp.utils.Color;
import ohos.app.Context;

public class Tabbar extends DependentLayout implements ITabbar<TabbarItemInfo<?>> {

    /**
     * 当前条目所对应的数据
     */
    private TabbarItemInfo<?> tabInfo;
    private Text mTabName;
    private Image mTabImage;

    public Tabbar(Context context) {
        this(context, null);
    }

    public Tabbar(Context context, AttrSet attrSet) {
        this(context, attrSet, "");
    }

    public Tabbar(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        Component component = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_tabbar_item_info, this, true);
        mTabImage = (Image) component.findComponentById(ResourceTable.Id_image);
        mTabName = (Text) component.findComponentById(ResourceTable.Id_name);
        mTabImage.setScaleMode(Image.ScaleMode.INSIDE);
    }

    /**
     * 设置条目的数据
     *
     * @param data
     */
    @Override
    public void setBarInfo(TabbarItemInfo<?> data) {
        tabInfo = data;
        inflateInfo(false, true);
    }

    /**
     * 初始化条目
     *
     * @param selected true 选中
     * @param init true 初始化
     */
    private void inflateInfo(boolean selected, boolean init) {
        if (tabInfo.tabType == TabbarItemInfo.BarType.IMAGE_TEXT) {
            if (init) {
                mTabName.setVisibility(VISIBLE);
                mTabImage.setVisibility(VISIBLE);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    mTabName.setText(tabInfo.name);
                }
            }
            if (selected) {
                mTabImage.setPixelMap(tabInfo.selectedImage);
                mTabName.setTextColor(new Color(parseColor(tabInfo.tintColor)));
            } else {
                mTabImage.setPixelMap(tabInfo.defaultImage);
                mTabName.setTextColor(new Color(parseColor(tabInfo.defaultColor)));
            }
        } else if (tabInfo.tabType == TabbarItemInfo.BarType.IMAGE) {
            if (init) {
                mTabName.setVisibility(HIDE);
                mTabImage.setVisibility(VISIBLE);
            }
            if (selected) {
                mTabImage.setPixelMap(tabInfo.selectedImage);
            } else {
                mTabImage.setPixelMap(tabInfo.defaultImage);
            }
        }
    }

    private int parseColor(Object color) {
        if (color instanceof String) {
            return Color.getIntColor((String) color);
        } else {
            return (int) color;
        }
    }

    /**
     * 动态修改某个tab的高度
     *
     * @param height tab的高度
     */
    @Override
    public void resetHeight(int height) {
        ComponentContainer.LayoutConfig config = getLayoutConfig();
        config.height = height;
        setLayoutConfig(config);
        mTabName.setVisibility(HIDE);
    }

    /**
     * 当某个条目被选中后的回调，该方法会被调用多次
     *
     * @param index 点击后选中条目的下标
     * @param preInfo 点击前选中的条目
     * @param nextInfo 点击后选中的条目
     */
    @Override
    public void onBarSelectedChange(int index, TabbarItemInfo<?> preInfo, TabbarItemInfo<?> nextInfo) {
        if (nextInfo.tabType == TabbarItemInfo.BarType.IMAGE) {
            return;
        }
        if (preInfo == nextInfo) {
            // 假设当前选中的是tab1，同时点击的也是tab1，那就不需要做任何操作了
            return;
        }
        if (preInfo != tabInfo && nextInfo != tabInfo) {
            /**
             * 假设有三个条目，条目1、条目2、条目3，preInfo是条目1，nextInfo是条目3，tabInfo是条目2，
             * 点击前选中的是条目1，点击后选中的条目3，此时条目2就不需要做任何操作了
             */
            return;
        }
        if (preInfo == tabInfo) {
            // 将点击前的条目反选
            inflateInfo(false, false);
        } else {
            // 选中被点击的条目
            inflateInfo(true, false);
        }
    }

    public TabbarItemInfo<?> getTabInfo() {
        return tabInfo;
    }

    public Text getTabName() {
        return mTabName;
    }

    public Image getImage() {
        return mTabImage;
    }
}