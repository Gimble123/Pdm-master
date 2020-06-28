package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Aula21Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula21);

        Intent intent = getIntent();

        String celcius = intent.getStringExtra("celcius");

        TextView textView3 = findViewById(R.id.textView3);

        double cel = Double.parseDouble(celcius);
        double far = (cel*9/5) + 32;

        textView3.setText(String.valueOf(far));


    }
}
