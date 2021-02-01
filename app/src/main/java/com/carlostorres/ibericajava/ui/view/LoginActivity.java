package com.carlostorres.ibericajava.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.carlostorres.ibericajava.R;
import com.carlostorres.ibericajava.data.local.prefrns.PrefernsProvidr;
import com.carlostorres.ibericajava.databinding.ActivityLoginBinding;
import com.carlostorres.ibericajava.model.User;
import com.carlostorres.ibericajava.model.intrfacs.LoginListener;
import com.carlostorres.ibericajava.ui.viewmodel.LoginVM;
import com.carlostorres.ibericajava.ui.viewmodel.UserVMFactory;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginVM viewModel = ViewModelProviders.of(this, new UserVMFactory( this )).get(LoginVM.class);
        loginBinding.setLogin(viewModel);

        validateSession();
    }

    @Override
    public void onSuccess() {
        EditText userID = findViewById(R.id.etUser);
        PrefernsProvidr.saveUser(userID.getText().toString(), this);
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailed() {
        Toast.makeText(this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
    }


    private void validateSession() {

        if ( PrefernsProvidr.getUser(this) != null && !PrefernsProvidr.getUser(this).equals("") ) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}