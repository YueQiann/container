package com.qianyue.container.common.excel.call;

import com.qianyue.container.common.excel.base.Excel;
import com.qianyue.container.common.excel.client.ExcleClient;


public class RealCall implements Call {
    final ExcleClient client;

    final Excel original;

    // Guarded by this.
    private boolean executed;

    public RealCall(ExcleClient client, Excel original) {
        this.client = client;
        this.original = original;
    }

    @Override
    public void execute() {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }
        original.create();
        original.fillData();
        original.close();
    }

    @Override
    public void enqueue(CallBack callback) {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }
        client.getDispatcher().enqueue(new AsyncCall(callback));
    }

    @Override
    public Call clone() {
        return new RealCall(client, original);
    }

    public final class AsyncCall extends NamedRunnable {
        private final CallBack callback;

        AsyncCall(CallBack callback) {
            this.callback = callback;
        }

        @Override
        protected void execute() {
            if (callback != null)
                original.setCallBack(callback);
            original.create();
            original.fillData();
            original.close();
        }
    }

}
