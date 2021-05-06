package com.jachdev.commonlibs.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.jachdev.commonlibs.R;
import com.jachdev.commonlibs.utils.AppTimeout;

import androidx.annotation.NonNull;

public class ProgressDialog extends Dialog implements AppTimeout.OnTimeoutListener {

    private AppTimeout mTimeout;

    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

        mTimeout = new AppTimeout(this);
        mTimeout.setTimeout(60 * 1000L);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cl_progress_layout);
        setCancelable(true);
    }

    @Override
    public void show() {
        super.show();

        mTimeout.start();
    }

    @Override
    public void OnTimeout() {
        if (isShowing()) {
            this.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        setCancelable(true);
        super.onBackPressed();
    }
}
