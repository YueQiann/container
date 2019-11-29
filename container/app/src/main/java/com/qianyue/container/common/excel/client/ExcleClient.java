package com.qianyue.container.common.excel.client;

import com.qianyue.container.common.excel.call.Call;
import com.qianyue.container.common.excel.base.Excel;
import com.qianyue.container.common.excel.call.RealCall;
import com.qianyue.container.common.excel.call.Dispatcher;

public class ExcleClient implements Call.Factory {
    final Dispatcher dispatcher;

    public ExcleClient() {
        this(new Builder());
    }

    public ExcleClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    @Override
    public Call newCall(Excel excel) {
        return new RealCall(this, excel);
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        Dispatcher dispatcher;

        public Builder() {
            dispatcher = new Dispatcher();
        }

        public Builder(ExcleClient client) {
            this.dispatcher = client.dispatcher;
        }

        public ExcleClient build() {
            return new ExcleClient(this);
        }
    }
}
