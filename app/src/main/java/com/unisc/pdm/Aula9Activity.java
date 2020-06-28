package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Aula9Activity extends AppCompatActivity implements SensorEventListener {

    private TextView a, b, c, d, e, f, g, h, i;
    SensorManager smn;
    Sensor sensor;
    LinearLayout box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula9);

        a = findViewById(R.id.a1);
        b = findViewById(R.id.a2);
        c = findViewById(R.id.a3);
        d = findViewById(R.id.a4);
        e = findViewById(R.id.a5);
        f = findViewById(R.id.a6);
        g = findViewById(R.id.a7);
        h = findViewById(R.id.a8);
        i = findViewById(R.id.a9);
        box = findViewById(R.id.box);

        smn = (SensorManager) getSystemService(SENSOR_SERVICE);
        smn.registerListener(this,smn.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        smn.registerListener(this,smn.getDefaultSensor(Sensor.TYPE_PROXIMITY),SensorManager.SENSOR_DELAY_NORMAL);
        smn.registerListener(this,smn.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_NORMAL);
        smn.registerListener(this,smn.getDefaultSensor(Sensor.TYPE_PRESSURE),SensorManager.SENSOR_DELAY_NORMAL);
        smn.registerListener(this,smn.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            a.setText("Accel X = " + sensorEvent.values[0]);
            b.setText("Accel Y = " + sensorEvent.values[1]);
            c.setText("Accel Z = " + sensorEvent.values[2]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            d.setText("Proximity = " + sensorEvent.values[0]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            e.setText("Light = " + sensorEvent.values[0]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            f.setText("Pressure = " + sensorEvent.values[0]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            g.setText("Orient X = " + sensorEvent.values[0]);
            h.setText("Orient Y = " + sensorEvent.values[1]);
            i.setText("Orient Z = " + sensorEvent.values[2]);

            float x = sensorEvent.values[0];
            float z = sensorEvent.values[2];

            if ((z > 1.2 && z < 1.8) || (z < -1.2 && z > -1.8)) {
                box.setBackgroundColor(Color.GREEN); //landscape
            }
            else {
                if ((z > -0.8 && z < 0.2) || (z < -2.84 && z > -3.44)) {
                    box.setBackgroundColor(Color.BLUE); //portrait
                }
            }

            if (x > 1.27 && x < 1.87)  {
                //finish(); fecha a activity
                System.exit(0);
            }
            else {
                if (x < -1.27 && x > -1.87) {
                    box.setBackgroundColor(Color.RED);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        smn.unregisterListener(this);
    }

    public void gpsClick(View view) {
        startActivity(new Intent(this,Aula9GpsActivity.class));
    }
}
