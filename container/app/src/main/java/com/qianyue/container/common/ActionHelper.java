package com.qianyue.container.common;

public class ActionHelper {

    public static void action(boolean rule, onActionListener listener) {
        if (rule) listener.onAction();
    }

    /**
     * 不为null 执行方法
     *
     * @param ob       任意对象
     * @param listener 需要执行的事件
     */
    public static void UnNullAction(Object ob, onActionListener listener) {
        action(ob != null, listener);
    }

    /**
     * 接口
     */
    public interface onActionListener {
        void onAction();
    }
}
