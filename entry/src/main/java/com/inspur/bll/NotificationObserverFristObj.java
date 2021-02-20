package com.inspur.bll;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.Dictionary;
import java.util.HashMap;

public class NotificationObserverFristObj implements NotificationObserver {

    private HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0x0022, "label");

    @Override
    public void executeSome(HashMap<String, Object> infos) {
        //HiLog.ERROR
        //DO
        HiLog.error(this.label, "observer go...");
    }
}
