package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OutraActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);

        Intent intent = getIntent();

        String valorRecebido = intent.getStringExtra("celcius");
        double celcius = Double.parseDouble(valorRecebido);
        double fahr = (celcius*9/5) + 32;

        TextView tv = findViewById(R.id.textView2);
        tv.setText(fahr + " Fahreinheit");


    }
}
