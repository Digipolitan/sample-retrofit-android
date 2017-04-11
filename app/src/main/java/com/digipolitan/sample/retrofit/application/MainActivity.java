package com.digipolitan.sample.retrofit.application;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.digipolitan.sample.retrofit.R;
import com.digipolitan.sample.retrofit.domain.user.model.User;
import com.digipolitan.sample.retrofit.domain.user.service.IUserService;
import com.digipolitan.sample.retrofit.platform.common.IServiceResultListener;
import com.digipolitan.sample.retrofit.platform.common.ServiceResult;
import com.digipolitan.sample.retrofit.platform.user.UserService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Julien BACZYNSKI http://www.digipolitan.com on 10/04/2017.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_login_layout)
    TextInputLayout login;
    @BindView(R.id.activity_main_password_layout)
    TextInputLayout password;
    @BindView(R.id.activity_main_firstname_layout)
    TextInputLayout firstName;
    @BindView(R.id.activity_main_lastname_layout)
    TextInputLayout lastName;
    @BindView(R.id.activity_main_user_field)
    TextView userField;
    private IUserService mUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_main_create)
    public void handleCreateClick() {
        if (validateForm()) {
            User u = new User();
            u.setId("ignoredID");
            u.setLogin(login.getEditText().getText().toString());
            u.setPassword(password.getEditText().getText().toString());
            u.setFirstName(firstName.getEditText().getText().toString());
            u.setLastName(lastName.getEditText().getText().toString());
            getUserService().create(u, new IServiceResultListener<String>() {
                @Override
                public void onResult(ServiceResult<String> result) {
                    getUserService().read(result.getData(), new IServiceResultListener<User>() {
                        @Override
                        public void onResult(ServiceResult<User> result) {
                            if (result.getError() == null)
                                userField.setText(result.getData().toString());
                            else
                                userField.setText(result.getError().getMessage());
                        }
                    });
                }
            });
        }
    }

    public IUserService getUserService() {
        if (mUserService == null)
            mUserService = new UserService();
        return mUserService;
    }

    public boolean validateForm() {
        boolean valid = true;
        if (TextUtils.isEmpty(login.getEditText().getText())) {
            login.setError(getString(R.string.mandatory));
            valid = false;
        }
        if (TextUtils.isEmpty(password.getEditText().getText())) {
            password.setError(getString(R.string.mandatory));
            valid = false;
        }
        if (TextUtils.isEmpty(firstName.getEditText().getText())) {
            firstName.setError(getString(R.string.mandatory));
            valid = false;
        }
        if (TextUtils.isEmpty(lastName.getEditText().getText())) {
            lastName.setError(getString(R.string.mandatory));
            valid = false;
        }

        return valid;
    }
}
