package com.carlostorres.ibericajava.model;

import android.text.TextUtils;

import androidx.annotation.Nullable;

public class User {

    @Nullable
    String userId, password;

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    @Nullable
    public String getUserId() {
        return userId;
    }

    public void setUserId(@Nullable String userId) {
        this.userId = userId;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }

    public boolean isValid() {

        return !TextUtils.isEmpty(userId) && !TextUtils.isEmpty(password);

    }
}
