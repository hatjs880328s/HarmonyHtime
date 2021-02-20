package com.inspur.bll;

//public class NotificationObserver {
//
//}

import java.util.Dictionary;
import java.util.HashMap;

public interface NotificationObserver {
    public void executeSome(HashMap<String, Object> infos);
}
