package com.jachdev.commonlibs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jachdev.commonlibs.R;
import com.jachdev.commonlibs.utils.CircleTransform;
import com.jachdev.commonlibs.utils.Helper;

import androidx.appcompat.widget.AppCompatImageView;

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Load image from piccaso lib
     *
     * @param path url or file path of the image
     */
    public void loadImage(String path) {

        Helper.loadImage(this, path, R.drawable.cl_ic_placeholder, R.drawable.cl_ic_placeholder);
    }

    /**
     * Load image from piccaso lib
     *
     * @param path url or file path of the image
     * @param placeholder Image place holder for empty images
     * @param errorPlaceholder If image failed to load
     */
    public void loadImage(String path, int placeholder, int errorPlaceholder) {
        Helper.loadImage(this, path, placeholder, errorPlaceholder);
    }

    /**
     * Load image from piccaso lib
     *
     * @param path url or file path of the image
     */
    public void loadImage(String circleTransform, String path) {
        Helper.loadImage(this, path, R.drawable.cl_ic_placeholder, R.drawable.cl_ic_placeholder, new CircleTransform(
                200, 0
        ));
    }

    /**
     * Load image from piccaso lib
     *
     * @param path url or file path of the image
     * @param scaleType selected scaling option for the image
     */
    public void loadAndScaleImage(String path, ImageView.ScaleType scaleType) {

        Helper.loadImage(this, path, scaleType);
    }

    /**
     * Load image from url/ file path Without displaying placeholder and scaling image
     *
     * @param path url or file path of the image
     */
    public void loadPlainImage(String path) {

        Helper.loadPlainImage(this, path);
    }
}
