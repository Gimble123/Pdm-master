package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aula8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula8);
    }

    public void selClick(View view) {
        startActivity(new Intent(this,Aula8SelActivity.class));
    }

    public void ex2Click(View view) {
        startActivity(new Intent(this,Aula8ExActivity.class));
    }
}
