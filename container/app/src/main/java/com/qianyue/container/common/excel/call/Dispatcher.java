package com.qianyue.container.common.excel.call;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Dispatcher {
    private ExecutorService executorService;

    public Dispatcher() {

    }

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = Executors.newCachedThreadPool();
        }
        return executorService;
    }

    synchronized void enqueue(RealCall.AsyncCall call) {
        executorService().execute(call);
    }
}
