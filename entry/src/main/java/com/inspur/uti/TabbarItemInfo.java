package com.inspur.uti;

public class TabbarItemInfo<Color> {

    public enum BarType {
        /**
         * 显示图片和文案
         */
        IMAGE_TEXT,
        /**
         * 只显示图片
         */
        IMAGE
    }

    /**
     * 默认的图片
     */
    public int defaultImage;
    /**
     * 选中后的图片
     */
    public int selectedImage;
    /**
     * 默认的文字颜色
     */
    public Color defaultColor;
    /**
     * 选中后的文字颜色
     */
    public Color tintColor;
    /**
     * 条目的名称
     */
    public String name;
    public BarType tabType;

    public TabbarItemInfo(String name, int defaultImage, int selectedImage) {
        this.name = name;
        this.defaultImage = defaultImage;
        this.selectedImage = selectedImage;
        this.tabType = BarType.IMAGE;
    }

    public TabbarItemInfo(String name, int defaultImage, int selectedImage, Color defaultColor, Color tintColor) {
        this.name = name;
        this.defaultImage = defaultImage;
        this.selectedImage = selectedImage;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = BarType.IMAGE_TEXT;
    }
}