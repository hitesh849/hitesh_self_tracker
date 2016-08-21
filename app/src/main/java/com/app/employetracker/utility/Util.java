package com.app.employetracker.utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.employetracker.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.byteclues.lib.init.Env;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by u on 2/15/2016.
 */
public class Util {
    public static ProgressDialog progressDialog;
    public static Snackbar retrySnackbar;
    public static View view;
    private static int pageLevel;

    public static Dialog showProDialog(Context context) {
        try {

            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // progressDialog.setCancelable(false);
                progressDialog.setMessage("Please Wait..");
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;
    }


    public static Dialog dimissProDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;
    }


    public static void setImageWithPiccaso(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView);
    }

    public static boolean passwordPatternValidation(String pass) {
        boolean b = true;
        String PASSWORD_PATTERN = "^.*[a-zA-Z].*$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(pass);
        if (!matcher.matches() || !pass.matches(".*\\d.*")) {
            b = false;
        }
        return b;
    }

    public static boolean isDeviceOnline() {
        boolean isDeviceOnLine = false;
        try {
            if (retrySnackbar != null)
                retrySnackbar.dismiss();
            ConnectivityManager cm = (ConnectivityManager) Env.appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            isDeviceOnLine = netInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeviceOnLine;
    }

    public static void showOKSnakBar(View view, String msg) {
        try {
            if (view != null && msg != null) {
                retrySnackbar = Snackbar
                        .make(view, msg, Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                retrySnackbar.dismiss();
                            }
                        });
                ViewGroup group = (ViewGroup) retrySnackbar.getView();
                group.setBackgroundColor(Env.appContext.getResources().getColor(R.color.colorPrimaryDark));
                retrySnackbar.setActionTextColor(Color.WHITE);
                View sbView = retrySnackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);

                textView.setTextColor(Color.WHITE);
                retrySnackbar.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static String getDeviceMacAddres(Context context) {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String macAddress = info.getMacAddress();
        if (macAddress == null) {
            manager.setWifiEnabled(true);
            final WifiInfo wifiInfo = manager.getConnectionInfo();
            macAddress = wifiInfo.getMacAddress();
        }
        return macAddress;
    }

    public static void showOKSnakBarTheemm(View view, Context context, String msg) {
        try {
            if (view != null && msg != null) {
                retrySnackbar = Snackbar
                        .make(view, msg, Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                retrySnackbar.dismiss();


                            }
                        });
                ViewGroup group = (ViewGroup) retrySnackbar.getView();
                group.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
                retrySnackbar.setActionTextColor(Color.WHITE);
                View sbView = retrySnackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);

                textView.setTextColor(Color.WHITE);
                retrySnackbar.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Snackbar showRetrySnakBarWithTheam(View view, final Context context, String msg) {

        try {
            if (view != null && msg != null) {
                retrySnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
                retrySnackbar.setActionTextColor(Color.WHITE);

                ViewGroup group = (ViewGroup) retrySnackbar.getView();
                group.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));

                View sbView = retrySnackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);

                textView.setTextColor(Color.WHITE);
                retrySnackbar.show();
                return retrySnackbar;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    public static void showRetrySnakBar(View view, final Context context, String msg) {

        try {
            retrySnackbar = Snackbar
                    .make(view, msg, Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((FragmentActivity) context).onBackPressed();
                        }
                    });

            retrySnackbar.setActionTextColor(Color.RED);
            View sbView = retrySnackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            retrySnackbar.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public static void showSnakBar(View view, String msg, Context context) {

        try {
            if (view != null && msg != null) {
                Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                ViewGroup group = (ViewGroup) snackbar.getView();
                group.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void setPageLevel() {
        ++pageLevel;
    }

    public static int getPageLevel() {
        return pageLevel;
    }

    public static void reSetPageLevel() {
        pageLevel = 0;
    }

    public static boolean mobileNumberValidation(View view, Context context, String mobileNum) {
        if (mobileNum.trim().length() == 0) {
            Util.showOKSnakBar(view, context.getString(R.string.enter_valid_phone));
        } else if (mobileNum.trim().length() != 0) {

            if (Util.isNumeric(mobileNum) && mobileNum.trim().length() != Constants.MAXLENGTHOFPHONENUMBER) {
                Util.showOKSnakBar(view, context.getString(R.string.phone_num_error_msg));
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean userNameValidation(View view, Context context, String userName) {
        Pattern pattern = Pattern.compile("^[@A-Za-z0-9_-]{3,15}$");
        Matcher matcher = pattern.matcher(userName);
        if (!matcher.matches()) {
            Util.showOKSnakBar(view, context.getString(R.string.userName_msg));
        }
        return matcher.matches();
    }

    public static boolean imeiValidation(View view, Context context, String imei) {

        if (imei.length() > 0) {
            return true;
        } else {
            Util.showOKSnakBar(view, context.getString(R.string.imei_cannot_be_empty));
            return false;
        }

    }

    public static boolean routeValidation(View view, Context context, String routeName) {

        if (routeName.length() > 0) {
            return true;
        } else {
            Util.showOKSnakBar(view, context.getString(R.string.route_name_cannot_be_empty));
            return false;
        }
    }


    public static boolean firstNameValidation(View view, Context context, String firstName) {

        if (!(firstName.trim().length() > 0)) {
            Util.showOKSnakBar(view, context.getString(R.string.first_name_cannot_be_empty));
        } else if (!specialCharValidation(firstName.trim())) {
            Util.showOKSnakBar(view, context.getString(R.string.first_name_only_special_msg));
        } else if (firstName.trim().length() < Constants.MINIMUMLENGTHOFNAME) {
            Util.showOKSnakBar(view, context.getString(R.string.first_name_error_msg));
        } else {
            return true;
        }
        return false;
    }

    public static boolean lastNameValidation(View view, Context context, String firstName) {

        if (!(firstName.trim().length() > 0)) {
            Util.showOKSnakBar(view, context.getString(R.string.last_cannot_be_empty));
        } else if (!specialCharValidation(firstName.trim())) {
            Util.showOKSnakBar(view, context.getString(R.string.last_name_only_special_msg));
        } else if (firstName.trim().length() < Constants.MINIMUMLENGTHOFNAME) {
            Util.showOKSnakBar(view, context.getString(R.string.last_name_error_msg));
        } else {
            return true;
        }
        return false;
    }


    public static boolean checkStringsContainsOnlySpecialChar(String inputString) {
        boolean found = false;
        String splChrs = "/^[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$/";
        found = inputString.matches("[" + splChrs + "]+");
        return found;
    }

    public static boolean specialCharValidation(String inputString) {
        boolean found = false;
        String splChrs = "[a-zA-Z\\s]+";
        found = inputString.matches("[" + splChrs + "]+");
        return found;
    }

    public static boolean cityValidation(View view, String name, Context context) {
        if (name.trim().length() < Constants.MINIMUMLENGTHOFNAME) {
            Util.showOKSnakBar(view, context.getString(R.string.city_error_msg));
        } else if (!name.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) {
            Util.showOKSnakBar(view, context.getString(R.string.city_only_special_msg));
        } else {
            return true;
        }
        return false;
    }

    public static boolean stateValidation(View view, String name, Context context) {
        if (name.trim().length() < Constants.MINIMUMLENGTHOFNAME) {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.state_error_msg));
        } else if (!name.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.state_only_special_msg));
        } else if (Util.isNumeric(name)) {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.state_only_numeric_msg));
        } else if (name.trim().length() > Constants.MAXIMUMLENGTHOFNAME) {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.state_name_error_msg_max));
        } else {
            return true;
        }
        return false;
    }

    public static boolean zipCodeValidation(View view, String name, Context context) {
        if (name.trim().length() < Constants.MINIMUMLENGTHOFNAME) {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.zipcode_error_msg));
        } else {
            return true;
        }
        return false;
    }

    public static boolean emailValilidation(View view, Context context, String eMailId) {
        if (eMailId.trim().length() != 0 && android.util.Patterns.EMAIL_ADDRESS.matcher(eMailId).matches()) {
            return true;
        } else {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.email_error_msg));
        }
        return false;
    }

    public static boolean addressValilidation(View view, Context context, String address) {
        if (address.length() > 2) {
            return true;
        } else {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.address_error));
        }
        return false;
    }

    public static boolean passValidation(View view, Context context, String password) {
        if (password.trim().length() < Constants.MINIMUMLENGTHOFPASS) {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.password_must_error_msg));
        } else {
            return true;
        }
        /*else if (!Util.passwordPatternValidation(password)) {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.msg_password_must_contain_numeric_special_character));
        }*/

        return false;
    }

    public static boolean passRepassValidation(View view, Context context, String password, String rePassword) {
        if (!password.equals(rePassword)) {
            Util.showOKSnakBar(view, context.getResources().getString(R.string.pass_do_not_match_error_msg));
        } else {
            return true;
        }
        return false;
    }

    public static boolean otpValilidation(String eMailId) {
        if (eMailId.length() > Constants.MINIMUMLENGTHOFNAME) {
            return true;
        }
        return false;
    }


    public static String getWarningMessage(JsonElement jsonElement) {

        if (jsonElement != null) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject != null) {
                JsonElement jsonElement1 = jsonObject.get("warning");
                if (jsonElement1 != null)
                    return jsonElement1.getAsString();
            }
        }
        return null;

    }

    public static void hideKeyBoardMethod(Context con, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) con.getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showKeyBoardMethod(Context con) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) con.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getDisplayWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        return width;
    }

    public static Date convert24HrTimeToTimeObj(String t) {
        DateFormat outputFormat = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = outputFormat.parse(t);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getString24HTime(String reportingTime) {

        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final Date dateObj = sdf.parse(reportingTime);
            String timein12Format = new SimpleDateFormat("hh:mm a").format(dateObj);
            return timein12Format;
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getDateTime(String str) {

        String[] array = new String[2];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = null;
        try {
            d = dateFormat.parse(str);
            DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat time = new SimpleDateFormat("hh:mm a");

            array[0] = date.format(d);
            array[1] = time.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm");
        return format.format(date);
    }

   /* public static dialog showattachmentdialog(view.onclicklistener clicklistener,context context)
    {
        dialog dialog=new dialog(context);
        dialog.requestwindowfeature(window.feature_no_title);
        dialog.setcontentview(r.layout.attachment_dialog);
        textview txtcemara=(textview)dialog.findviewbyid(r.id.txtcemara);
        textview txtexstingimage=(textview)dialog.findviewbyid(r.id.txtexstingimage);
        textview txtcancel=(textview)dialog.findviewbyid(r.id.txtcancel);
        txtcemara.setonclicklistener(clicklistener);
        txtexstingimage.setonclicklistener(clicklistener);
        txtcancel.setonclicklistener(clicklistener);
        window window=dialog.getwindow();
        window.setgravity(gravity.bottom);
        window.setlayout(linearlayout.layoutparams.match_parent, linearlayout.layoutparams.match_parent);
        window.setbackgrounddrawable(new colordrawable(color.transparent));
        dialog.show();
        return dialog;
    }*/


}
