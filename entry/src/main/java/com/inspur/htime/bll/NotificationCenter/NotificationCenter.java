package com.inspur.htime.bll.NotificationCenter;

import java.util.ArrayList;
import java.util.HashMap;

/// 通知中心
public class NotificationCenter {

    private static NotificationCenter instance;

    private NotificationCenter() { super(); }

    /// 单例获取类（目前不处理多线程信息）
    public static NotificationCenter getInstance() {
        if (NotificationCenter.instance == null) {
            NotificationCenter.instance = new NotificationCenter();
        }
        return NotificationCenter.instance;
    }

    /// 监听者数组
    private HashMap<String, ArrayList<NotificationObserver>> observers
            = new HashMap<String, ArrayList<NotificationObserver>>();

    /// 添加一个监听
    public void addObserver(String notificationName, NotificationObserver obj) {
        if (this.observers.get(notificationName) != null) {
            ArrayList<NotificationObserver> elements
                    = (ArrayList<NotificationObserver>)this.observers.get(notificationName);
            elements.add(obj);
        } else {
            ArrayList<NotificationObserver> newList = new ArrayList<NotificationObserver>();
            newList.add(obj);
            this.observers.put(notificationName,newList);
        }
    }

    /// 移除所有监听
    public void removeAllObserver() {
        //TODO: remove
    }

    /// 发送通知
    public void postNotification(String notificationName, HashMap<String, Object> infos) {
        if (this.observers.get(notificationName) != null) {
            ArrayList<NotificationObserver> elements
                    = (ArrayList<NotificationObserver>)this.observers.get(notificationName);
            for (NotificationObserver eachItem : elements) {
                if (eachItem != null) {
                    eachItem.executeSome(infos);
                }
            }
        } else {
            // have no observer notificate it...
        }
    }
}
