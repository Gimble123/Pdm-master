package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aula3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula3);
    }

    public void aula31Click(View view) {
        startActivity(new Intent(this,Aula31Activity.class));
    }

    public void aula32Click(View view) {
        startActivity(new Intent(this,Aula32Activity.class));
    }

    public void aula33Click(View view) {
        startActivity(new Intent(this,Aula33Activity.class));
    }

    public void aula34Click(View view) { startActivity(new Intent(this,Aula34Activity.class));
    }
}
