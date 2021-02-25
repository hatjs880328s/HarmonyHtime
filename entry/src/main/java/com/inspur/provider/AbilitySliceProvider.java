package com.inspur.provider;

import ohos.agp.components.Component;
import ohos.app.Context;
import ohos.global.resource.ResourceManager;

public interface AbilitySliceProvider {

    /**
     * 系统已有的方法，不需要单独实现
     *
     * @param id
     * @return
     */
    Component findComponentById(int id);

    /**
     * 系统已有的方法，不需要单独实现
     *
     * @return
     */
    ResourceManager getResourceManager();

    /**
     * 获取string.json文件中定义的字符串
     *
     * @param resId
     * @return
     */
    String getString(int resId);

    /**
     * 获取color.json文件中定义的颜色值
     *
     * @param colorId
     * @return
     */
    int getColor(int colorId);

    /**
     * 获取上下文，系统已有的方法，不需要单独实现
     *
     * @return
     */
    Context getContext();
}
