package com.inspur.htime.bll.NotificationCenter;

//public class NotificationObserver {
//
//}

import java.util.HashMap;

public interface NotificationObserver {
    public void executeSome(HashMap<String, Object> infos);
}
