package com.example.muf_mayer_zainzinger.viewmodel;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.muf_mayer_zainzinger.data.SensorData;
import java.util.List;

public class DataViewModel extends BaseViewModel {

    private final Handler handler = new Handler(Looper.getMainLooper());
    public final LiveData<SensorData> dataLiveData;

    public DataViewModel(@NonNull Application application) {
        super(application);
        dataLiveData = new AccLiveData(application.getApplicationContext());
    }

    public void insert(List<SensorData> accData) {
        Runnable r = () -> {
            for (SensorData acceleration : accData) {
                getDatabase().getDataDao().insert(acceleration);
            }
        };
        Thread t = new Thread(r);
        t.start();
    }


    public LiveData<List<SensorData>> getAccLiveData(){
        return getDatabase().getDataDao().getAllData();
    }

    private static final class AccLiveData extends LiveData<SensorData> {
        private final SensorManager sm;
        private final Sensor accelerometer;
        private final Sensor gravitySensor;
        private float[] gravity;

        private final SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                switch (event.sensor.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        final SensorData sensorData = new SensorData();
                        float[] values = removeGravity(gravity, event.values);
                        sensorData.setXYZ(values[0], values[1], values[2]);
                        sensorData.setSensor(event.sensor);
                        setValue(sensorData);
                        break;
                    case Sensor.TYPE_GRAVITY:
                        gravity = event.values;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };

        AccLiveData(Context context){
            sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            if (sm != null) {
                gravitySensor = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
                accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            } else {
                throw new RuntimeException("STOP");
            }
        }

        @Override
        protected void onActive() {
            super.onActive();
            sm.registerListener(listener, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
            sm.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        protected void onInactive() {
            super.onInactive();
            sm.unregisterListener(listener);
        }

        private float[] removeGravity(float[] gravity, float[] values) {
            if (gravity == null) {
                return values;
            }
            final float alpha = 0.8f;
            float g[] = new float[3];
            g[0] = alpha * gravity[0] + (1 - alpha) * values[0];
            g[1] = alpha * gravity[1] + (1 - alpha) * values[1];
            g[2] = alpha * gravity[2] + (1 - alpha) * values[2];

            return new float[]{
                    values[0] - g[0],
                    values[1] - g[1],
                    values[2] - g[2]
            };
        }
    }
    }



