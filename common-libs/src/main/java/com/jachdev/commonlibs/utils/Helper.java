package com.jachdev.commonlibs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.jachdev.commonlibs.R;
import com.jachdev.commonlibs.widget.CustomImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Locale;

public class Helper {

    private static final String TAG = Helper.class.getSimpleName();

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

    public static void loadImage(ImageView view, String path, int placeholder, int errorPlaceholder, CircleTransform circleTransform) {

        if (path == null || path.isEmpty()) {
            view.setImageResource(R.drawable.cl_ic_placeholder);
            return;
        }

        File file = new File(path);

        if (file.isFile()) {
            Picasso.get()
                    .load(file)
                    .transform(circleTransform)
                    .placeholder(placeholder)
                    .error(errorPlaceholder)
                    .into(view);
        } else {
            Picasso.get()
                    .load(path)
                    .transform(circleTransform)
                    .placeholder(placeholder)
                    .error(errorPlaceholder)
                    .into(view);
        }

        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
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

    public static void updateResources(Context context) {
        final String LANGUAGE_SYSTEM_DEFAULT = "LANGUAGE_SYSTEM_DEFAULT";
        final String SELECTED_LANGUAGE = "SELECTED_LANGUAGE";
        final String SELECTED_COUNTRY = "SELECTED_COUNTRY";

        Locale locale;
        String language = new TinyDb(context).getString(SELECTED_LANGUAGE);
        String country = new TinyDb(context).getString(SELECTED_COUNTRY);

        if (!language.isEmpty() && !country.isEmpty()) {
            locale = new Locale(language, country);
        }
        else if (language.isEmpty() || language.equals(LANGUAGE_SYSTEM_DEFAULT)) {
            locale = Locale.getDefault();
        }
        else {
            locale = new Locale(language);
        }

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        }
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());

    }

    public static Point getDisplayMetrics(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Point point = new Point();
        point.x = width;
        point.y = height;

        Log.d(TAG, "getDisplayMetrics: " + point.toString());

        return point;
    }
}
