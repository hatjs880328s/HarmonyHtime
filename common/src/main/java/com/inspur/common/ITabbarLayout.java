package com.inspur.common;

import ohos.agp.components.ComponentContainer;

import java.util.List;

public interface ITabbarLayout<Bar extends ComponentContainer, D> {

    /**
     * 根据数据查找条目
     *
     * @param data 数据
     * @return 条目
     */
    Bar findBar(D data);

    /**
     * 添加监听
     *
     * @param listener
     */
    void addBarSelectedChangeListener(OnBarSelectedListener<D> listener);

    /**
     * 默认选中的条目
     *
     * @param defaultInfo
     */
    void defaultSelected(D defaultInfo);

    /**
     * 初始化所有的条目
     *
     * @param infoList
     */
    void initInfo(List<D> infoList);

    interface OnBarSelectedListener<D> {

        /**
         * 当某个条目被选中后的回调，该方法会被调用多次
         *
         * @param index 点击后选中条目的下标
         * @param preInfo 点击前选中的条目
         * @param nextInfo 点击后选中的条目
         */
        void onBarSelectedChange(int index, D preInfo, D nextInfo);
    }
}