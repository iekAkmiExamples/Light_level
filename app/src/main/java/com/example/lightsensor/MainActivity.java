package com.example.lightsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lightsensor.eventListeners.LightSensorActionListener;

public class MainActivity extends AppCompatActivity {
    private TextView lightLevelView;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private LightSensorActionListener lightSensor_AL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // UI elements
        lightLevelView = findViewById(R.id.LightLevel);

        //sensors
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightSensor_AL = new LightSensorActionListener(lightLevelView, this.getApplicationContext());

        //check if sensor exists
        if (lightSensor != null) {
            // Success!
            Toast.makeText(MainActivity.this, "All sensors are available", Toast.LENGTH_SHORT).show();
        } else {
            // Failure!
            Toast.makeText(MainActivity.this, "Not all sensors are available", Toast.LENGTH_SHORT).show();
            //stop process
        }
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(lightSensor_AL, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(lightSensor_AL);
    }
}