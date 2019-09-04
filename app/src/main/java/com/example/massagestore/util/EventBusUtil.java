package com.example.massagestore.util;

import com.example.massagestore.eventbus.EventMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by cbh on 2019/3/7
 * e-mail:yoursilib@qq.com
 */
public class EventBusUtil {

    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)){
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        clearEvent();
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(EventMessage event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(EventMessage event) {
        EventBus.getDefault().postSticky(event);
    }

    /**
     *  EventBus 粘性事件遇到的坑：
     *      点击按钮跳转页面并没有sendEvent，可跳转过去依然接收到了事件。需清空EventBus 否则会拿上次的缓存发送
     */
    public static void clearEvent() {
        EventMessage stickyEvent = EventBus.getDefault().getStickyEvent(EventMessage.class);
        if (stickyEvent != null){
            EventBus.getDefault().removeAllStickyEvents();
        }
    }
}
