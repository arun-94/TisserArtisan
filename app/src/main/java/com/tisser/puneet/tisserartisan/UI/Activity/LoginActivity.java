package com.tisser.puneet.tisserartisan.UI.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.tisser.puneet.tisserartisan.Global.Constants;
import com.tisser.puneet.tisserartisan.HTTP.LoginService;
import com.tisser.puneet.tisserartisan.HTTP.ServiceGenerator;
import com.tisser.puneet.tisserartisan.R;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends BaseActivity implements Validator.ValidationListener
{

    @Bind(R.id.loginButton) Button mLoginButton;
    @Email @Bind(R.id.editText_custid) EditText mCustIdEditText;
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE) @Bind(R.id.editText_password) EditText mPasswordEditText;

    @OnClick(R.id.loginButton)
    void login()
    {
        loginValidator.validate();
    }

    //private CircularProgressView mProgressBar;
    private Validator loginValidator;
    private SharedPreferences settings;

    //boolean allowLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        loginValidator = new Validator(this);
        loginValidator.setValidationListener(this);

        settings = getSharedPreferences(Constants.PREFS_NAME, 0);

        if (settings.getBoolean(Constants.PREFS_IS_LOGGED_IN, false))
        {
            openNextActivity();
        }
    }

    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_login;

    }

    @Override
    protected void setupToolbar()
    {
    }

    @Override
    protected void setupLayout()
    {

    }

    void openNextActivity()
    {
        navigator.navigateToBaseActivity_NavDrawer(LoginActivity.this);
    }

    @Override
    public void onValidationSucceeded()
    {
        settings.edit().putBoolean(Constants.PREFS_IS_LOGGED_IN, true).commit();
        loginPostCall(mCustIdEditText.getText().toString(), mPasswordEditText.getText().toString());
        //openNextActivity();
    }

    private void loginPostCall(String userId, String password)
    {
        String action = "validateUser";
        LoginService service = ServiceGenerator.createService(LoginService.class, LoginService.BASE_URL);

        service.validate(action, userId,  password, new Callback<String>()
        {
            @Override
            public void success(String s, Response response)
            {
                Log.e("Login", "success" + s);
                Log.e("Data", "" + response);
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.e("Login", "error");
                Log.e("Data", "" + error);
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors)
    {
        for (ValidationError error : errors)
        {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText)
            {
                ((EditText) view).setError(message);
            }
            else
            {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

            if (view instanceof TextView)
            {
                ((TextView) view).setError(message);
            }
            else
            {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
