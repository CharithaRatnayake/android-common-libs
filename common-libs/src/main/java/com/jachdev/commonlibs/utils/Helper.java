package com.jachdev.commonlibs.utils;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.jachdev.commonlibs.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DecimalFormat;

public class Helper {

    public static int getInt(String text) {
        try {
            return text == null || text.isEmpty() ?
                    0 : Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String toCurrencyFormat(double price) {
        DecimalFormat df2 = new DecimalFormat("0.00");
        return df2.format(price);
    }

    public static String checkIfNull(String name) {
        return name == null ? "" : name;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Load image from url/ file path
     *  @param view ImageView
     * @param path Path of the file or image url
     * @param placeholder Image place holder for empty images
     * @param errorPlaceholder If image failed to load
     */
    public static void loadImage(ImageView view, String path, int placeholder, int errorPlaceholder) {

        if (path == null || path.isEmpty()) {
            view.setImageResource(R.drawable.cl_ic_placeholder);
            return;
        }

        File file = new File(path);

        if (file.isFile()) {
            Picasso.get()
                    .load(file)
                    .placeholder(placeholder)
                    .error(errorPlaceholder)
                    .into(view);
        } else {
            Picasso.get()
                    .load(path)
                    .placeholder(placeholder)
                    .error(errorPlaceholder)
                    .into(view);
        }

        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    /**
     * Load image from url/ file path
     *
     * @param view ImageView
     * @param path Path of the file or image url
     */
    public static void loadImage(ImageView view, String path) {

        if (path == null || path.isEmpty()) {
            view.setImageResource(R.drawable.cl_ic_placeholder);
            return;
        }

        File file = new File(path);

        if (file.isFile()) {
            Picasso.get()
                    .load(file)
                    .placeholder(R.drawable.cl_ic_placeholder)
                    .error(R.drawable.cl_ic_placeholder)
                    .into(view);
        } else {
            Picasso.get()
                    .load(path)
                    .placeholder(R.drawable.cl_ic_placeholder)
                    .error(R.drawable.cl_ic_placeholder)
                    .into(view);
        }

        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
