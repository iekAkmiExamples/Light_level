package com.example.lightsensor.eventListeners;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.icu.text.DecimalFormat;
import android.widget.TextView;

public class LightSensorActionListener implements SensorEventListener {
    private TextView lightLevelView;
    private Context activityContext;

    public LightSensorActionListener(TextView lightLevelView, Context activityContext) {
        this.lightLevelView = lightLevelView;
        this.activityContext = activityContext;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float lightLevel = sensorEvent.values[0];

        this.lightLevelView.setText(brightness(lightLevel) + "\nThe light level is " + lightLevel);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private String brightness(float lightLevel) {
        if (lightLevel == 0)
            return "Pitch black";
        else if (lightLevel > 0 && lightLevel <= 1)
            return "Dark";
        else if (lightLevel > 1 && lightLevel <= 50)
            return "Less than normal light";
        else if (lightLevel > 50 && lightLevel <= 5000)
            return "Normal";
        else
            return "Too bright";
    }
}
