package com.qianyue.container.widget;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class GridLayoutManager extends RecyclerView.LayoutManager {
    private int offsetX = 0; //水平偏移

    private int row; // 行
    private int column;// 列
    private int count; // 数据一页的总数
    private int pageSize; // 页面总数
    private int totalWidth; // 最大滚动量

    public GridLayoutManager(int row, int column) {
        this.row = row;
        this.column = column;
        count = row * column;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.i("位移", "scrollHorizontallyBy: " + dx);
//        //移动元素
//        offsetChildrenHorizontal(-dx);
//        offsetX -= dx;
//
////        return super.scrollHorizontallyBy(dx, recycler, state);
//        return -dx;
        int newX = offsetX + dx;
        int result = dx;
        if (newX > totalWidth) {
            result = totalWidth - offsetX;
        } else if (newX < 0) {
            result = 0 - offsetX;
        }
        offsetX += result;
        offsetChildrenHorizontal(-result);
//        recycleAndFillItems(recycler, state);
        return result;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //没有数据就不进行处理
        if (getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        if (state.isPreLayout()) {
            return;
        }
        //将所有的子view临时移除并且回收
        detachAndScrapAttachedViews(recycler);

        //进行布局
        offsetX = 0;
        // 总页面
        pageSize = getItemCount() / count;
        //计算可以横向滚动的最大值
        totalWidth = (pageSize - 1) * getWidth();
        for (int i = 0; i < getItemCount(); i++) {
            View child = recycler.getViewForPosition(i);
            addView(child);
            measureAndLayout(child, i);
        }
        layoutItems(recycler, state);
    }

    /**
     * 进行布局
     *
     * @param view
     * @param position
     */
    public void measureAndLayout(View view, int position) {
        //开始计算大小
        measureChildWithMargins(view, 0, 0);
        //计算宽度
        int width = getDecoratedMeasuredWidth(view);
        //计算高度
        int height = getDecoratedMeasuredHeight(view);
        // 当前页 从零开始
        int page = position / count;
        // 第几列
        int c = position % column;
        // 第几行(以一页为参考)
        int r = position / column;
        // 总长度
        int w = getUsableWidth();
        int left = c * width + page * w;
        int top = r * height;
        int right = left + width;
        int buttom = top + height;

        Log.i("列表", "页数=" + page + "   行=" + r + "    列=" + c + "===========" +
                "   left=" + left + "   top=" + top + "   right=" + right + "   buttom=" + buttom);

        //将view放置在RecyclerView里面
        layoutDecoratedWithMargins(view, left, top, right, buttom);
        offsetX += w;
    }

    private void layoutItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) return;

    }

    private int getUsableWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getUsableHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        offsetX = 0;
    }

    // 总范围
    @Override
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return pageSize * getWidth();
    }

    // 移动
    @Override
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return offsetX;
    }

    // 显示
    @Override
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return getWidth();
    }
}
