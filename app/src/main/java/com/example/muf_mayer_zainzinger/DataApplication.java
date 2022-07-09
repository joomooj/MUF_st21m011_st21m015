package com.example.muf_mayer_zainzinger;

import android.app.Application;
import androidx.room.Room;

public class DataApplication extends Application {
    private SensorDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room
                .databaseBuilder(this, SensorDatabase.class,"data")
                .build();
    }

    public SensorDatabase getDatabase(){
        return database;
    }
}
