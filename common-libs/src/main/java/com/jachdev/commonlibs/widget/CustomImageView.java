package com.jachdev.commonlibs.widget;

import android.content.Context;
import android.util.AttributeSet;

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
}
