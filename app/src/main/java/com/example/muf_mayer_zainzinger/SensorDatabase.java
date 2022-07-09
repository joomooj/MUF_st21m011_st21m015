package com.example.muf_mayer_zainzinger;

import androidx.room.RoomDatabase;

import com.example.muf_mayer_zainzinger.dao.DataDao;
import com.example.muf_mayer_zainzinger.data.SensorData;

//@Database(entities = {SensorData.class}, version = 1, exportSchema = false)

@androidx.room.Database(entities = {SensorData.class}, version = 2, exportSchema = false )
public abstract class SensorDatabase extends RoomDatabase {
    public abstract DataDao getDataDao();
}
