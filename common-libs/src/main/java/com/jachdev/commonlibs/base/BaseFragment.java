package com.jachdev.commonlibs.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    private static final String TAG = BaseFragment.class.getSimpleName();

    private Unbinder unbinder;
    private BaseActivity activity;

    private View mRootView;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        activity = (BaseActivity) context;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public BaseActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    public View getRootView() {
        return mRootView;
    }

    public void showMessage(String message) {
        activity.showMessage(message);
    }

    public void showWaiting() {
        activity.showWaiting();
    }

    public void dismissWaiting() {
        activity.dismissWaiting();
    }
}