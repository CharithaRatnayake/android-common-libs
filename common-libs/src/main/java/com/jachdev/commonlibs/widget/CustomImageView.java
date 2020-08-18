package com.jachdev.commonlibs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

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

        Helper.loadImage(this, path);
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
