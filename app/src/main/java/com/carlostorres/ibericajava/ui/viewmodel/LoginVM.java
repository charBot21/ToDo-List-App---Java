package com.carlostorres.ibericajava.ui.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.lifecycle.ViewModel;

import com.carlostorres.ibericajava.model.User;
import com.carlostorres.ibericajava.model.intrfacs.LoginListener;

public class LoginVM extends ViewModel {

    private User user;
    private LoginListener listener;

    public LoginVM(LoginListener listener) {
        this.listener = listener;
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginListener getListener() {
        return listener;
    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
    }

    public TextWatcher userTextWacher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                user.setUserId(editable.toString());
            }
        };
    }

    public TextWatcher passwordTextWacher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                user.setPassword(editable.toString());
            }
        };

    }

    public void onLogin(View view) {

        if ( !user.isValid() ) {
            listener.onFailed();
        } else {
            listener.onSuccess();
        }

    }
}
