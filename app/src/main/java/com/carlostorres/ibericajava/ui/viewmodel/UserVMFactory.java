package com.carlostorres.ibericajava.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.carlostorres.ibericajava.model.intrfacs.LoginListener;

public class UserVMFactory extends ViewModelProvider.NewInstanceFactory {

    private LoginListener listener;

    public UserVMFactory(LoginListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginVM(listener);
    }
}
