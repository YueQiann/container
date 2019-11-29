package com.qianyue.container.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class BasePresenter<V extends BaseView, T> implements LifeCycleListener {
    protected Reference<V> mViewRef;
    protected V mView;
    protected Reference<T> mActivityRef;
    protected T mAcitity;

    public BasePresenter(V view, T activity) {
        attachView(view);
        attachActivity(activity);
        setListener(activity);
    }

    private void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
        if (mViewRef != null)
            mView = mViewRef.get();
    }

    private void attachActivity(T activity) {
        mActivityRef = new WeakReference<T>(activity);
        if (mActivityRef != null)
            mAcitity = mActivityRef.get();
    }

    private void setListener(T activity) {
        if (activity != null) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) activity).setLifeCycleListener(this);
            }
        }
    }

    /**
     * 销毁view
     */
    private void detachView() {
        if (mViewRef != null)
            mViewRef.clear();
        mViewRef = null;
        mView = null;
    }

    private void detachActivity() {
        if (mActivityRef != null)
            mActivityRef.clear();
        mActivityRef = null;
        mAcitity = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        detachView();
        detachActivity();
    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {

    }

    @Override
    public void onActivityCreated(Bundle bundle) {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }
}
