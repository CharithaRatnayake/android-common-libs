package com.jachdev.commonlibs.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jachdev.commonlibs.R;
import com.jachdev.commonlibs.dialog.ProgressDialog;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private SparseArray<Fragment> mFragments = new SparseArray<>();

    private ProgressDialog mProgressDialog;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutRes() != 0) {
            setContentView(layoutRes());
        }
        ButterKnife.bind(this);
    }

    /**
     * Change the Status bar color value
     * @param res = Color resource value
     */
    protected void setStatusBarColor(int res){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            if(window == null){
                Log.e(TAG, "setStatusBarColor: Error window found");
                return;
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(res);
        }
    }

    /**
     * Start activity from class name
     *
     * @param activityClass class name
     */
    public void activityToActivity(Class activityClass) {
        Intent intent = new Intent(getApplication(), activityClass);
        startActivity(intent);
    }

    public void activityToActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    public void showWaiting() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, R.style.cl_progress_bar);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissWaiting() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * @param containerId       View id
     * @param fragment          Fragment that need to commit
     * @param isBackStackEnable Back stack enable or not
     */
    protected void startFragment(int containerId, Fragment fragment, boolean isBackStackEnable) {
        int key = mFragments.size();
        mFragments.put(key, fragment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isBackStackEnable) transaction.addToBackStack(String.valueOf(key));

        transaction.replace(containerId, fragment);
        transaction.commit();
        Log.d(TAG, "startFragment: " + fragment.getClass().getSimpleName() + " at " + key);
    }

    public Fragment getFragment() {
        return mFragments.valueAt(mFragments.size() - 1);
    }

    /**
     * Show general massages in toast text
     *
     * @param message Toast text message
     */
    public void showMessage(String message) {
        if (message == null || message.isEmpty()) return;

        Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }
}
