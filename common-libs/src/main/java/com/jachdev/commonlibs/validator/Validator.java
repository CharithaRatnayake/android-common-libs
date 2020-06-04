package com.jachdev.commonlibs.validator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;

import com.google.android.material.textfield.TextInputLayout;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.jachdev.commonlibs.R;
import com.jachdev.commonlibs.widget.CustomEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String TAG = Validator.class.getSimpleName();
    // \w = [a-zA-Z_0-9] || \d = [0-9] || \s = whitespace[\t\n\x0B\f\r] || \Q : Quote all characters up to \E
    private static final String FORMAT_PHONE_1 = "07\\d{8}";
    private static final String FORMAT_PHONE_2 = "+947\\d{8}";

    private static final String FORMAT_NUMBER = "07\\d{8}";
    private static final String FORMAT_TELEPHONE_NUMBER = "0\\d{9}";
    private static final String FORMAT_NIC_NUMBER = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
    private static final String FORMAT_PASSPORT = "^[a-zA-Z]\\d{7}$";
    private static final String FORMAT_DRIVING_LICENSE_NUMBER = "^[a-zA-Z]\\d{6}$|^[a-zA-Z]\\d{9}$";
    private static final String FORMAT_PASSWORD = "^[\\w\\s\\Q!\"#$%&'()*+,-.\\/:;<=>?@[]^_`{|}~\\E]+$";
    private static final String FORMAT_EMAIL = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
    private static final String FORMAT_USER_NAME = "[\\w_. -]+";
    // \w = [a-zA-Z_0-9] || \d = [0-9] || \s = whitespace[\t\n\x0B\f\r] || \Q : Quote all characters up to \E
    @SuppressLint("StaticFieldLeak")
    private static TextInputLayout sTextInputLayout;
    @SuppressLint("StaticFieldLeak")
    private static CustomEditText mCustomEditText;
    @SuppressLint("StaticFieldLeak")
    private static String sUserInputText;


    public static boolean isValidField(CustomEditText editTextView) {
        initComponent(editTextView);

        if (sUserInputText.equals("")) {
            return showErrorMessage(R.string.cl_error_this_field_is_required);
        }
        return true;
    }

    /**
     * Validate User name and Password
     *
     * @param userNameView : userName's CustomEditText
     * @param passwordView : password's CustomEditText
     * @return [UserName, Password ] > both valid = true ||> one or both invalid = false
     */
    public static boolean isValidUserNameAndPassword(CustomEditText userNameView, CustomEditText passwordView) {
        boolean isValidUserName = isValidPhoneNumber(userNameView);
        boolean isValidPassword = isValidPassword(passwordView);
        return isValidUserName && isValidPassword;
    }

    public static boolean isEditTextEmpty(CustomEditText editTextView, String error) {
        initComponent(editTextView);
        if (sUserInputText.isEmpty()) {
            return showErrorMessage(error);
        }
        return true;
    }

    /**
     * Validate user's phone number
     *
     * @param editTextView : PhoneNumber's CustomEditText
     * @return Valid Format = true || Invalid = false
     */
    public static boolean isValidPhoneNumber(CustomEditText editTextView) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(R.string.cl_error_phone_number_empty);
        }
        if (isInvalidFormat(FORMAT_PHONE_1)) {
            return showErrorMessage(R.string.cl_error_phone_number_format);
        }
        return true;
    }


    /**
     * Validate user's phone number
     *
     * @param countryCode : Country code of the mobile phone
     * @param etPhoneNumber : PhoneNumber's CustomEditText
     * @return Valid Format = true || Invalid = false
     */
    public static boolean isValidPhoneNumber(int countryCode, CustomEditText etPhoneNumber) {
        initComponent(etPhoneNumber);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(R.string.cl_error_phone_number_empty);
        }

        Phonenumber.PhoneNumber phoneNumber =
                new Phonenumber.PhoneNumber()
                        .setCountryCode(countryCode).setNationalNumber(etPhoneNumber.getTextInt());

        if(PhoneNumberUtil.getInstance().isValidNumber(phoneNumber)){
            return true;
        }

        return showErrorMessage(R.string.cl_error_phone_number_empty);
    }

    /**
     * Validate user telephone number
     *
     * @param editTextView : TelephoneNumber's CustomEditText
     * @return Valid Format = true || Invalid = false
     */
    public static boolean isValidTelephoneNumber(CustomEditText editTextView) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(R.string.cl_error_phone_number_empty);
        }
        if (isInvalidFormat(FORMAT_TELEPHONE_NUMBER)) {
            return showErrorMessage(R.string.cl_error_telephone_number_format);
        }
        return true;
    }

    public static boolean isValidNIC(CustomEditText editTextView, String error) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(error);
        }
        if (isInvalidFormat(FORMAT_NIC_NUMBER)) {
            return showErrorMessage(R.string.cl_error_nic_number_format);
        }
        return true;
    }

    public static boolean isValidDrivingLicense(CustomEditText editTextView, String error) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(error);
        }
        if (isInvalidFormat(FORMAT_DRIVING_LICENSE_NUMBER)) {
            return showErrorMessage(R.string.cl_error_license_number_format);
        }
        return true;
    }

    public static boolean isValidPassport(CustomEditText editTextView, String error) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(error);
        }
        if (isInvalidFormat(FORMAT_PASSPORT)) {
            return showErrorMessage(R.string.cl_error_passport_number_format);
        }
        return true;
    }

    /**
     * Validate password
     *
     * @param editTextView : Password's CustomEditText
     * @return Valid Format = true || Invalid = false
     */
    public static boolean isValidPassword(CustomEditText editTextView) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(R.string.cl_error_password_empty);
        }
        if (sUserInputText.length() < 8) {
            return showErrorMessage(R.string.cl_error_password_size);
        }
        if (isInvalidFormat(FORMAT_PASSWORD)) {
            return showErrorMessage(R.string.cl_error_password_format);
        }
        return true;
    }

    public static boolean isValidPasswordField(CustomEditText editTextView) {
        Resources res = editTextView.getContext().getResources();

        if (editTextView.getTrimText().isEmpty()) {
            editTextView.setError(res.getString(R.string.cl_error_password_empty));
            return false;
        }
        if (editTextView.getTrimText().length() < 8) {
            editTextView.setError(res.getString(R.string.cl_error_password_size));
            return false;
        }

        Matcher matcher = Pattern.compile(FORMAT_PASSWORD).matcher(editTextView.getTrimText());
        if (!matcher.matches()) {
            editTextView.setError(res.getString(R.string.cl_error_password_format));
            return false;
        }

        return true;
    }

    public static boolean isCurrency(CustomEditText editTextView) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(R.string.cl_error_this_field_is_required);
        }
        try {
            double v = Double.valueOf(editTextView.getText().toString());
            if (v <= 0) {
                return showErrorMessage(R.string.cl_error_input_valid_field);
            }
        } catch (Exception e) {
            return showErrorMessage(R.string.cl_error_input_valid_field);
        }

        return true;
    }

    public static boolean isNumber(CustomEditText editTextView) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(R.string.cl_error_this_field_is_required);
        }
        try {
            int v = Integer.valueOf(editTextView.getText().toString());
            if (v <= 0) {
                return showErrorMessage(R.string.cl_error_input_valid_field);
            }
        } catch (Exception e) {
            return showErrorMessage(R.string.cl_error_input_valid_field);
        }

        return true;
    }

    public static boolean isValidChangedPassword(CustomEditText newPassword, CustomEditText confirmPassword) {
        initComponent(confirmPassword);
        if (!newPassword.getText().toString().equals(sUserInputText)) {
            return showErrorMessage(R.string.cl_error_user_password_mismatch);
        }
        return true;
    }

    public static String isValidOneTimePassword(CustomEditText digit1, CustomEditText digit2,
                                                CustomEditText digit3, CustomEditText digit4) {
        return digit1.getText().toString() + digit2.getText().toString() +
                digit3.getText().toString() + digit4.getText().toString();
    }

    /**
     * Validate email address
     *
     * @param editTextView : Password's CustomEditText
     * @return Valid Format = true || Invalid = false
     */
    public static boolean isValidEmail(CustomEditText editTextView) {
        initComponent(editTextView);

        if (sUserInputText.isEmpty()) {
            return showErrorMessage(R.string.cl_error_email_empty);
        }
        if (isInvalidFormat(FORMAT_EMAIL)) {
            return showErrorMessage(R.string.cl_error_email_format);
        }
        return true;
    }

    public static boolean verifierEmail(CustomEditText editTextView) {
        String value;
        try {
            value = editTextView.getText().toString().trim();
        } catch (NullPointerException npe) {
            value = "";
        }

        if (value.isEmpty()) return false;

        Pattern pattern = Pattern.compile(FORMAT_EMAIL);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean isValidTextField(CustomEditText editTextView) {
        String value;
        try {
            value = editTextView.getText().toString().trim();
        } catch (NullPointerException npe) {
            value = "";
        }
        return (!value.isEmpty());
    }

    /**
     * Validate user name
     *
     * @param editTextView : UserName's CustomEditText
     * @return Valid Format = true || Invalid = false
     */
    public static boolean isValidUserName(CustomEditText editTextView) {
        initComponent(editTextView);

        if (sUserInputText == null || sUserInputText.isEmpty()) {
            return showErrorMessage(R.string.cl_error_user_name_empty);
        }
        if (isInvalidFormat(FORMAT_USER_NAME)) {
            return showErrorMessage(R.string.cl_error_user_name_format);
        }
        return true;
    }

    public static boolean verifierUserName(CustomEditText editTextView) {
        String value;
        try {
            value = editTextView.getText().toString().trim();
        } catch (NullPointerException npe) {
            value = "";
        }

        if (value.isEmpty()) return false;

        Pattern pattern = Pattern.compile(FORMAT_USER_NAME);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * Init "sTextInputLayout" using CustomEditText and
     * init "sUserInputText" using CustomEditText's text
     *
     * @param editTextView : CustomEditText Object
     */
    private static void initComponent(CustomEditText editTextView) {
        sTextInputLayout = null;
        mCustomEditText = null;

        if (editTextView.getParent().getParent() instanceof TextInputLayout) {
            sTextInputLayout = (TextInputLayout) editTextView.getParent().getParent();
            sTextInputLayout.setErrorEnabled(false);
        }else{
            mCustomEditText = editTextView;
        }

        try {
            sUserInputText = editTextView.getTrimText();

        } catch (NullPointerException npe) {
            sUserInputText = "";
        }
    }

    /**
     * Validate user input with the given pattern
     *
     * @param PATTERN : [String] validation pattern
     * @return : is pattern not matching with input => TRUE else return FALSE
     */
    private static boolean isInvalidFormat(final String PATTERN) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(sUserInputText);
        return !matcher.matches();
    }


    /**
     * Display error message in the "TextInputLayout" and
     * return false for indicate this is an error type
     *
     * @param resId : [int] string resource id
     * @return : FALSE
     */
    private static boolean showErrorMessage(int resId) {
        Context context = (sTextInputLayout != null ? sTextInputLayout : mCustomEditText).getContext();
        String message = context.getResources().getString(resId);
        return showErrorMessage(message);
    }

    private static boolean showErrorMessage(String message) {
        if(sTextInputLayout != null){
            sTextInputLayout.setErrorEnabled(true);
            sTextInputLayout.setError(message);
        }else{
            mCustomEditText.setError(message);
        }
        return false;
    }

    public static void showErrorMessage(TextInputLayout inputLayout, int resId) {
        Context context = inputLayout.getContext();
        String message = context.getResources().getString(resId);
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(message);

    }

    public static void hideNonEditTextErrorMessage(TextInputLayout inputLayout) {
        if (inputLayout.isErrorEnabled())
            inputLayout.setError(null);
    }

    public static boolean isValidQtyWithMax(CustomEditText etQty, CustomEditText etMaxQty) {

        return (isNumber(etQty) && isNumber(etMaxQty) &&
                etQty.getTextInt() > etMaxQty.getTextInt());
    }
}