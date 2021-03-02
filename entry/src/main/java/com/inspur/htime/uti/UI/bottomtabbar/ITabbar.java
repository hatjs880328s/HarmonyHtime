package com.inspur.htime.uti.UI.bottomtabbar;

public interface ITabbar<D> extends ITabbarLayout.OnBarSelectedListener<D> {

    /**
     * 设置条目的数据
     *
     * @param data
     */
    void setBarInfo(D data);

    /**
     * 动态修改某个条目的大小
     *
     * @param height
     */
    void resetHeight(int height);
}