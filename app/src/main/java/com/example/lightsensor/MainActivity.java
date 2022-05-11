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
    //UI elements
    private TextView lightLevelView;
    //Sensor code variables
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private LightSensorActionListener lightSensor_AL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // UI elements
        this.lightLevelView = findViewById(R.id.LightLevel);

        //sensors
        this.sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.lightSensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        this.lightSensor_AL = new LightSensorActionListener(this.lightLevelView);

        //check if sensor exists
        if (this.lightSensor != null) {
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
        super.onResume();
        // Register a listener for the sensor.
        this.sensorManager.registerListener(this.lightSensor_AL, this.lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Be sure to unregister the sensor when the activity pauses.
        this.sensorManager.unregisterListener(this.lightSensor_AL);
    }
}