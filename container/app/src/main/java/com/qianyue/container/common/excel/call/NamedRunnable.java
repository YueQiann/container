package com.qianyue.container.common.excel.call;

public abstract class NamedRunnable implements Runnable {
    @Override
    public void run() {
        execute();
    }

    protected abstract void execute();
}
