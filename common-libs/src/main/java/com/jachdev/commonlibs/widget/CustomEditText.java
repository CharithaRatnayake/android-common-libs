package com.jachdev.commonlibs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.jachdev.commonlibs.R;
import com.jachdev.commonlibs.utils.DateTimeUtil;
import com.jachdev.commonlibs.utils.Helper;
import com.jachdev.commonlibs.validator.Validator;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText extends AppCompatEditText {

    private static final String TAG = CustomEditText.class.getSimpleName();
    private PasswordView mPasswordView;

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
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
     * Set text null no exception
     *
     * @param text Set text null no exception
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

    /**
     * @return Get text string value return empty text if null
     */
    public String getTextString() {
        return getText() == null ? "" : getText().toString();
    }

    /**
     * @return get the int value of the custom edit text
     */
    public int getTextInt() {
        return Helper.getInt(getTextString());
    }

    /**
     * @return get the double value of the custom edit text
     */
    public double getTextDouble() {
        try {
            return getText() == null || getText().toString().isEmpty() ?
                    0 : Double.parseDouble(getText().toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Set date and time '''DateTimeUtil.DEFAULT_DATE_FORMAT''' format
     * @param unix  Unix timestamp format
     * @param format Parse DateTimeUtil.DEFAULT_DATE_FORMAT, DateTimeUtil.DATE_FORMAT_1 ...
     */
    public void setDateTime(long unix, String format) {
        setText(DateTimeUtil.unixToDateTime(unix, format));
    }

    /**
     * Set the text as a currency in edit text
     *
     * @param type  Currency type
     * @param value Currency value
     */
    public void setCurrency(String type, double value) {
        String currency = type == null ? "LKR" : type + " " + Helper.toCurrencyFormat(value);
        setText(currency);
    }

    /**
     * @return the trim text which removed extra space
     */
    public String getTrimText() {
        return getText() == null ? "" : getText().toString().trim();
    }

    /**
     * Set editable false if you want to deactivate the edittext
     *
     * @param status of the edit text editability
     */
    public void setEditability(boolean status) {
        setEnabled(status);
        setFocusable(status);
        setFocusableInTouchMode(status);
        setClickable(status);
        setCursorVisible(status);
    }


    /**
     * EditText bind as a password field
     * @param view
     */
    public void bindPasswordView(View view){
        mPasswordView = new PasswordView(view, this);
    }

    /**
     * get PasswordView object and give access to all passwordField methods
     * @return PasswordView
     */
    public PasswordView asPasswordView(){
        return mPasswordView;
    }

    public void removeErrorMessage() {
        setError(null);
    }

    private TextWatcher mPasswordChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d(TAG, "afterTextChanged: " + s.toString());
            if (!(mPasswordView.isPasswordToggleIconVisible())) {
                asPasswordView().setPasswordToggleIconEnable(true);
                removeErrorMessage();
            }
        }
    };

    /**
     *PasswordView class holds useful password field helper methods (password eye visibility, validation)
     */
    public class PasswordView{
        private TextInputLayout mTextInputLayout;
        private CustomEditText mCustomEditText;

        private PasswordView(View view, CustomEditText customEditText){
            try {
                mCustomEditText = customEditText;
                mTextInputLayout = (TextInputLayout) view.findViewById(getId()).getParent().getParent();
                addTextChangedListener(mPasswordChange);
            } catch (NullPointerException npe) {
                Log.e(TAG, "CustomEditText : ", npe);
            }
        }


        /**
         * set TextInputLayout's password visibility toggle enable/disable
         * @param status ( true - enable | false - disable)
         */
        public void setPasswordToggleIconEnable(boolean status) {
            if (mTextInputLayout != null) {
                mTextInputLayout.setPasswordVisibilityToggleEnabled(status);
            }
        }

        /**
         * get TextInputLayout's password toggle visibility
         * @return boolean - (Icon visible - true | invisible - false)
         */
        public boolean isPasswordToggleIconVisible() {
            if (mTextInputLayout != null) {
                return mTextInputLayout.isPasswordVisibilityToggleEnabled();
            }
            return false;
        }

        /**
         * get password validity and manage password icon toggle and error message visibility
         * @return boolean - (valid password - true | invalid - false)
         */
        public boolean isValidPasswordField() {
            setPasswordToggleIconEnable(false);

            boolean isValid = Validator.isValidPasswordField(mCustomEditText);

            setPasswordToggleIconEnable(isValid);
            return isValid;
        }

        /**
         * check current field entered password is match with another field's password
         * @param customEditText - field that compete with
         * @return boolean - (both matched - true | not match - false)
         */
        public boolean isMatched(CustomEditText customEditText){
            return mCustomEditText.getTrimText().equals(customEditText.getTrimText());
        }
    }
}
