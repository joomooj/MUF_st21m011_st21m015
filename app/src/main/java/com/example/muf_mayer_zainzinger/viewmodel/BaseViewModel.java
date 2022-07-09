package com.example.muf_mayer_zainzinger.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.muf_mayer_zainzinger.DataApplication;
import com.example.muf_mayer_zainzinger.SensorDatabase;

public abstract class BaseViewModel extends AndroidViewModel {
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public SensorDatabase getDatabase() {
        return ((DataApplication)getApplication()).getDatabase();
    }
}
