package com.example.muf_mayer_zainzinger.data;

import android.hardware.Sensor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity(tableName = "SensorData")

public class SensorData {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @Ignore
    private Sensor sensor;

    @ColumnInfo(name = "x")
    private float x;
    @ColumnInfo(name = "y")
    private float y;
    @ColumnInfo(name = "z")
    private float z;



    public Sensor getSensor() {
        return sensor;
    }

    public int getId() {
        return id; }

    public void setId(int id) {
        this.id = id;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setXYZ(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Acceleration Data" +" "+
                "ID=" + id + "\n"+
                "x=" + x +
                ", y=" + y +
                ", z=" + z + "\n" ;
    }
}
