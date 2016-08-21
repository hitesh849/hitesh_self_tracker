package com.app.employetracker.viewControlers;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.employetracker.R;
import com.app.employetracker.dao.CommonData;
import com.app.employetracker.model.LogInModel;
import com.app.employetracker.sharedPreferences.Config;
import com.app.employetracker.utility.Constants;

import org.byteclues.lib.model.BasicModel;
import org.byteclues.lib.utils.Util;
import org.byteclues.lib.view.AbstractFragmentActivity;

import java.util.Observable;

import retrofit.RetrofitError;

/**
 * Created by admin on 07-08-2016.
 */

public class LogInActivity extends AbstractFragmentActivity implements View.OnClickListener {
    private LogInModel loginModel = new LogInModel();

    private EditText etxtUserNameLogin;
    private EditText etxtPasswordLogin;
    private Button btnSubmitLogin;

    @Override
    protected void onCreatePost(Bundle savedInstanceState) {
        setContentView(R.layout.login);
        if (Config.getLoginStatus()) {
            gotoDashboard();
        } else {
            init();
        }
    }

    private void init() {
        etxtUserNameLogin = (EditText) findViewById(R.id.etxtUserNameLogin);
        etxtPasswordLogin = (EditText) findViewById(R.id.etxtPasswordLogin);
        btnSubmitLogin = (Button) findViewById(R.id.btnSubmitLogin);
        btnSubmitLogin.setOnClickListener(this);

        etxtUserNameLogin.setText("yogendragpt@gmail.com");
        etxtPasswordLogin.setText("123456");
    }


    private void gotoDashboard() {
        startActivity(new Intent(this, HomeActivity.class));
        this.finish();
    }

    @Override
    protected BasicModel getModel() {
        return loginModel;
    }

    @Override
    public void update(Observable observable, Object o) {
        Util.dimissProDialog();
        if (o != null && o instanceof CommonData) {
            CommonData commonData = (CommonData) o;
            if (commonData.replyStatus.equals("success")) {
                Config.setLoginStatus(true);
                Config.savePreferences();
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else if (commonData.replyStatus.equals("error")) {
                Util.showAlertDialog(null, commonData.replyMsg);
            }
        } else if (o != null && o instanceof RetrofitError) {
            Util.showAlertDialog(null, "Oops! something went wrong!");
        }


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSubmitLogin) {
            String userName = etxtUserNameLogin.getText().toString();
            String password = etxtPasswordLogin.getText().toString();
            if (validateCredentials(userName, password)) {
                if (Util.isDeviceOnline()) {
                    Util.showProDialog(LogInActivity.this);
                    loginModel.doLogin(userName, password);
                } else {
                    Util.showCenteredToast(LogInActivity.this, Constants.INTERNET_ERROR_MSG);
                }

            }
        }
    }

    private boolean validateCredentials(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            etxtUserNameLogin.setError("Can't be Empty");
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 4 || password.length() > 10) {
            etxtPasswordLogin.setError("between 4 and 10 alphanumeric characters");
            return false;
        }
        return true;
    }

}