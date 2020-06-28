package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aula11Activity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula11);

        intent = new Intent(this, NotificacaoService.class);
    }

    public void startClick(View v) {
        startService(intent);
    }

    public void stopClick(View v) {
        stopService(intent);
    }
}
