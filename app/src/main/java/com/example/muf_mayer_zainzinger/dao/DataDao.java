package com.example.muf_mayer_zainzinger.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.muf_mayer_zainzinger.data.SensorData;

import java.util.List;

@Dao

public abstract class DataDao {

    @Query("SELECT * FROM SensorData")
    public abstract LiveData<List<SensorData>> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE) //IGNORE
    public abstract long insert(SensorData sensorData);

}
