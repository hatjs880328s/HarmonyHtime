package com.inspur.common;

import com.inspur.common.uti.DisplayUtils;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.StackLayout;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.LayoutAlignment;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

public class BottomTabbar extends StackLayout implements ITabbarLayout<Tabbar, TabbarItemInfo<?>> {

    private static final int ID_TAB_BOTTOM = 0XFF;
    /**
     * 事件监听的集合
     */
    private List<OnBarSelectedListener<TabbarItemInfo<?>>> tabSelectedListeners = new ArrayList<>();
    /**
     * 当前选中的条目
     */
    private TabbarItemInfo<?> selectedInfo;
    /**
     * 底部导航栏的透明度
     */
    private float barBottomAlpha = 1;
    /**
     * 底部导航栏的高度
     */
    private float barBottomHeight = 50;
    /**
     * 底部导航栏线条的高度
     */
    private float barBottomLineHeight = 0.5f;
    /**
     * 底部导航栏线条的颜色
     */
    private RgbColor barBottomLineColor = new RgbColor(223, 224, 225);
    /**
     * 所有的tab
     */
    private List<TabbarItemInfo<?>> infoList;

    public BottomTabbar(Context context) {
        this(context, null);
    }

    public BottomTabbar(Context context, AttrSet attrSet) {
        this(context, attrSet, "");
    }

    public BottomTabbar(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
    }

    /**
     * 根据数据查找条目
     *
     * @param info 条目的数据
     * @return 条目
     */
    @Override
    public Tabbar findBar(TabbarItemInfo<?> info) {
        ComponentContainer componentContainer = (ComponentContainer) findComponentById(ID_TAB_BOTTOM);
        for (int i = 0; i < componentContainer.getChildCount(); i++) {
            Component component = componentContainer.getComponentAt(i);
            if (component instanceof Tabbar) {
                Tabbar bottomBar = (Tabbar) component;
                if (bottomBar.getTabInfo() == info) {
                    return bottomBar;
                }
            }
        }
        return null;
    }

    /**
     * 添加监听
     *
     * @param listener 监听
     */
    @Override
    public void addBarSelectedChangeListener(OnBarSelectedListener<TabbarItemInfo<?>> listener) {
        tabSelectedListeners.add(listener);
    }

    /**
     * 默认选中的条目
     *
     * @param defaultInfo 默认选中条目的信息
     */
    @Override
    public void defaultSelected(TabbarItemInfo<?> defaultInfo) {
        onSelected(defaultInfo);
    }

    /**
     * 初始化所有的条目
     *
     * @param infoList 所有条目的信息
     */
    @Override
    public void initInfo(List<TabbarItemInfo<?>> infoList) {
        if (infoList == null || infoList.isEmpty()) {
            return;
        }
        this.infoList = infoList;
        // 移除之前已经添加的组件，防止重复添加
        removeComponent();
        selectedInfo = null;
        // 添加背景
        addBackground();
        // 添加条目
        addBottomBar();
        // 添加线条
        addBottomLine();
    }

    /**
     * 添加线条
     */
    private void addBottomLine() {
        Component line = new Component(getContext());
        // 目前不支持直接设置背景颜色，只能通过Element来设置背景
        ShapeElement element = new ShapeElement();
        element.setShape(ShapeElement.RECTANGLE);
        element.setRgbColor(barBottomLineColor);
        line.setBackground(element);
        LayoutConfig config = new LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT,
                DisplayUtils.vp2px(getContext(), barBottomLineHeight));
        // 位于底部
        config.alignment = LayoutAlignment.BOTTOM;
        config.setMarginBottom(DisplayUtils.vp2px(getContext(), barBottomHeight - barBottomLineHeight));
        line.setAlpha(barBottomAlpha);
        addComponent(line, config);
    }

    /**
     * 添加条目
     */
    private void addBottomBar() {
        int width = DisplayUtils.getDisplayWidthInPx(getContext()) / infoList.size();
        int height = DisplayUtils.vp2px(getContext(), barBottomHeight);
        StackLayout stackLayout = new StackLayout(getContext());
        stackLayout.setId(ID_TAB_BOTTOM);
        for (int i = 0; i < infoList.size(); i++) {
            TabbarItemInfo<?> info = infoList.get(i);
            LayoutConfig config = new LayoutConfig(width, height);
            config.alignment = LayoutAlignment.BOTTOM;
            config.setMarginLeft(i * width);
            Tabbar bottomBar = new Tabbar(getContext());
            tabSelectedListeners.add(bottomBar);
            bottomBar.setBarInfo(info);
            stackLayout.addComponent(bottomBar, config);
            bottomBar.setClickedListener(component -> onSelected(info));
        }
        LayoutConfig layoutConfig = new LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT,
                ComponentContainer.LayoutConfig.MATCH_CONTENT);
        layoutConfig.alignment = LayoutAlignment.BOTTOM;
        addComponent(stackLayout, layoutConfig);
    }

    private void onSelected(TabbarItemInfo<?> nextInfo) {
        for (OnBarSelectedListener<TabbarItemInfo<?>> listener : tabSelectedListeners) {
            listener.onBarSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        if (nextInfo.tabType == TabbarItemInfo.BarType.IMAGE_TEXT) {
            selectedInfo = nextInfo;
        }
    }

    /**
     * 添加背景
     */
    private void addBackground() {
        Component component = new Component(getContext());
        // 目前还不能直接设置背景颜色，只能通过Element来设置背景
        ShapeElement element = new ShapeElement();
        element.setShape(ShapeElement.RECTANGLE);
        RgbColor rgbColor = new RgbColor(255, 255, 255);
        element.setRgbColor(rgbColor);
        component.setBackground(element);
        component.setAlpha(barBottomAlpha);
        LayoutConfig config = new LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT,
                DisplayUtils.vp2px(getContext(), barBottomHeight));
        config.alignment = LayoutAlignment.BOTTOM;
        addComponent(component, config);
    }

    /**
     * 移除之前已经添加的组件，防止重复添加
     */
    private void removeComponent() {
        for (int i = getChildCount() - 1; i > 0; i--) {
            removeComponentAt(i);
        }
        tabSelectedListeners.removeIf(listener ->
                listener instanceof Tabbar);
    }

    /**
     * 设置底部导航栏的透明度
     *
     * @param barBottomAlpha 底部导航栏的透明度
     */
    public void setBarBottomAlpha(float barBottomAlpha) {
        this.barBottomAlpha = barBottomAlpha;
    }

    /**
     * 设置底部导航栏的高度
     *
     * @param barBottomHeight 底部导航栏的高度
     */
    public void setBarBottomHeight(float barBottomHeight) {
        this.barBottomHeight = barBottomHeight;
    }

    /**
     * 设置底部导航栏线条的高度
     *
     * @param barBottomLineHeight 底部导航栏线条的高度
     */
    public void setBarBottomLineHeight(float barBottomLineHeight) {
        this.barBottomLineHeight = barBottomLineHeight;
    }

    /**
     * 设置底部导航栏线条的颜色
     *
     * @param barBottomLineColor 底部导航栏线条的颜色
     */
    public void setBarBottomLineColor(RgbColor barBottomLineColor) {
        this.barBottomLineColor = barBottomLineColor;
    }
}
