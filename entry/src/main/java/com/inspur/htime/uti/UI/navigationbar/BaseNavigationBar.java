package com.inspur.htime.uti.UI.navigationbar;

import ohos.agp.components.*;
import ohos.app.Context;
//import ohos.global.systemres.ResourceTable;
import com.inspur.htime.ResourceTable;

import java.time.format.ResolverStyle;

public class BaseNavigationBar extends DirectionalLayout {

    public BaseNavigationBar(Context context) {
        this(context, null);
    }

    public BaseNavigationBar(Context context, AttrSet attrSet) {
        this(context, attrSet, "");
    }

    public BaseNavigationBar(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);

        Component com = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_layout_basenavigationbar, this, true);

    }


}
