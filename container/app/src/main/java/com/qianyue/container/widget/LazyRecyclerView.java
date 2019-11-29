package com.qianyue.container.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qianyue.container.application.App;

public class LazyRecyclerView extends RecyclerView {
    public LazyRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public LazyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LazyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case SCROLL_STATE_IDLE:
                        Glide.with(App.getContext().getApplicationContext()).resumeRequests();
                        break;
                    case SCROLL_STATE_DRAGGING:
                        Glide.with(App.getContext().getApplicationContext()).pauseRequests();
                        break;
                    case SCROLL_STATE_SETTLING:
                        Glide.with(App.getContext().getApplicationContext()).resumeRequests();
                        break;
                }


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

}
