package com.jachdev.commonlibs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import com.jachdev.commonlibs.R;
import com.jachdev.commonlibs.utils.Helper;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomButton extends AppCompatEditText {

    private static final String TAG = CustomButton.class.getSimpleName();

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(attrs);
    }

    private void setCustomFont(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomFont);
        String customFont = a.getString(R.styleable.CustomFont_fontType);
        setCustomFont(customFont);
        a.recycle();
    }

    /**
     * Set custom font
     *
     * @param text Assert path of the font
     */
    public void setAnyText(String text) {
        setText(Helper.checkIfNull(text));
    }

    /**
     * Set custom font
     *
     * @param asset Assert path of the font
     * @return The font changed or not
     */
    public boolean setCustomFont(String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(getContext().getAssets(), asset);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: " + e.getMessage());
            return false;
        }

        setTypeface(tf);
        return true;
    }
}
